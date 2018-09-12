package gameOfLife;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SizeSelect extends JFrame implements ActionListener {
	private static final long serialVersionUID = 9167182715512601687L;
	DoneCallback obj = null;
	JTextField widthBox = null;
	JTextField heightBox = null;

	SizeSelect(DoneCallback obj) {
		super("Game of Life | Pick Size");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.obj = obj;
		setSize(400, 100);
		setLayout(new GridLayout(3, 1));

		widthBox = new JTextField(10);
		JLabel widthLabel = new JLabel("Enter width: ");

		heightBox = new JTextField(10);
		JLabel heightLabel = new JLabel("Enter height: ");

		JPanel widthPanel = new JPanel();
		widthPanel.setLayout(new GridLayout(1, 2));
		widthPanel.add(widthLabel);
		widthPanel.add(widthBox);

		add(widthPanel);

		JPanel heightPanel = new JPanel();
		heightPanel.setLayout(new GridLayout(1, 2));
		heightPanel.add(heightLabel);
		heightPanel.add(heightBox);

		add(heightPanel);


		JButton startButton = new JButton("Start the program");
		startButton.addActionListener(this);
		add(startButton);
	}

	public void actionPerformed(ActionEvent e) {
		setVisible(false);
		obj.done(Integer.parseInt(heightBox.getText()), 
			Integer.parseInt(widthBox.getText()));
	}
}