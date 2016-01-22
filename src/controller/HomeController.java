package controller;

import java.net.URL;
import java.util.ResourceBundle;

import business.BookBusiness;
import business.MemberBusiness;
import business.UserBusineess;
import javafx.animation.TranslateTransitionBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Long Huynh
 */
public class HomeController implements Initializable {
	@FXML
	private Label lblTotalBook;
	@FXML
	private Label lblTotalMember;
	@FXML
	private Label lblTotalStaff;
	@FXML
	private Label lblTotalOverdueBook;
	@FXML
	private PieChart graphPieChart;
	@FXML
	private Label lblCheckoutBook;
	@FXML
	private Label lblAvailableBook;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		BookBusiness bookBusiness = new BookBusiness();
		MemberBusiness memberBusiness = new MemberBusiness();
		UserBusineess userBusineess = new UserBusineess();
		int totalBook = bookBusiness.getTotalBooks().size();

		int totalCheckouted = bookBusiness.getTotalBookCheckouted();
		int totalAvailable =  totalBook - totalCheckouted;
		
		lblCheckoutBook.setText(totalCheckouted + "");
		lblAvailableBook.setText(totalAvailable + "");
		
		lblTotalBook.setText(totalBook + "");
		lblTotalOverdueBook.setText(bookBusiness.getTotalBookOverDue() + "");
		lblTotalMember.setText(memberBusiness.getAll().size() + "");
		lblTotalStaff.setText(userBusineess.getAll().size() + "");
		
		
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Checkouted", totalCheckouted),
				new PieChart.Data("Available",totalAvailable));
		graphPieChart.setData(pieChartData);
		graphPieChart.setStyle("-fx-pie-label-visible: false");
		graphPieChart.setTitle("Library Books");
		for (final PieChart.Data data : pieChartData) {
	
			// data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new
			// EventHandler<MouseEvent>() {
			// @Override
			// public void handle(MouseEvent e) {
			// Tooltip.install(data.getNode(), tooltip);
			// tooltip.setText(String.valueOf(data.getName() + "\n" +
			// data.getPieValue()));
			// }
			// });

			data.getNode().setOnMouseEntered(new MouseHoverAnimation(data, graphPieChart));
			data.getNode().setOnMouseExited(new MouseExitAnimation());
		}
		graphPieChart.setClockwise(false);

	}
}

class MouseHoverAnimation implements EventHandler<MouseEvent> {

	static final Duration ANIMATION_DURATION = new Duration(500);
	static final double ANIMATION_DISTANCE = 0.05;
	private double cos;
	private double sin;
	private PieChart chart;

	public MouseHoverAnimation(PieChart.Data d, PieChart chart) {
		this.chart = chart;
		double start = 0;
		double angle = calcAngle(d);

		for (PieChart.Data tmp : chart.getData()) {

			if (tmp == d) {
				break;
			}
			start += calcAngle(tmp);
		}

		cos = Math.cos(Math.toRadians(0 - start - angle / 2));
		sin = Math.sin(Math.toRadians(0 - start - angle / 2));
	}

	@Override
	public void handle(MouseEvent arg0) {
		Node n = (Node) arg0.getSource();

		double minX = Double.MAX_VALUE;
		double maxX = Double.MAX_VALUE * -1;

		for (PieChart.Data d : chart.getData()) {
			minX = Math.min(minX, d.getNode().getBoundsInParent().getMinX());
			maxX = Math.max(maxX, d.getNode().getBoundsInParent().getMaxX());
		}

		double radius = maxX - minX;
		TranslateTransitionBuilder.create().toX((radius * ANIMATION_DISTANCE) * cos)
				.toY((radius * ANIMATION_DISTANCE) * sin).duration(ANIMATION_DURATION).node(n).build().play();
	}

	private static double calcAngle(PieChart.Data d) {
		double total = 0;
		for (PieChart.Data tmp : d.getChart().getData()) {
			total += tmp.getPieValue();
		}

		return 360 * (d.getPieValue() / total);
	}
}

class MouseExitAnimation implements EventHandler<MouseEvent> {
	@Override
	public void handle(MouseEvent event) {
		TranslateTransitionBuilder.create().toX(0).toY(0).duration(new Duration(500)).node((Node) event.getSource())
				.build().play();
	}
}