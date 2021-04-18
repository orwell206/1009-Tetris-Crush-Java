package UI_Engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScoreBoard extends JPanel {
	private List<String[]> lines = new ArrayList<String[]>();
	private String line;
	private String[][] data = {}; 
	private String[] columnNames = { "", "" }; 
	
	public ScoreBoard(JFrame mf) {
		/*
		 * Params : JFrame mf 
		 * 
		 * Desc : Default Constructor. 
		 * 
		 * Returns : None. 
		 */				
		// Set base JPanel to take full size of MainFrame
		setPreferredSize(new Dimension(MainFrame.WINDOW_WIDTH, MainFrame.WINDOW_HEIGHT));
		setBackground(Color.GRAY);
		setLayout(null);

		// Adds a 'back' button
		JButton btn_back = new JButton("");
		btn_back.setBackground(Color.WHITE);
		btn_back.setOpaque(false);
		btn_back.setIcon(new ImageIcon(ScoreBoard.class.getResource("/Assets/rsz_circled-left.png")));
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						JPanel contentPane = new MainMenu(mf);
						mf.getContentPane().removeAll();
						mf.setContentPane(contentPane);
						mf.revalidate();
						mf.repaint();
					}
				});
			}
		});
		btn_back.setBounds(0, 0, 153, 128);
		add(btn_back);
		
		// Label for title
		JLabel lblNewLabel_1 = new JLabel("SCORE BOARD");
		lblNewLabel_1.setForeground(Color.black);
		lblNewLabel_1.setFont(new Font("Eras Bold ITC", Font.PLAIN, 61));
		lblNewLabel_1.setBounds(223, 22, 528, 43);
		add(lblNewLabel_1);
		
		// Label for column names
		JLabel label = new JLabel("Player Name                   Total Score");
		Font font = new Font("Eras Bold ITC", Font.PLAIN, 25);
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		label.setFont(font.deriveFont(attributes));
		label.setBounds(268, 130, 600, 22);
		add(label);

		// Read saved leaderboard from file path
		try {
			File file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "Assets"
					+ File.separator + "leaderboard.txt");
			boolean exists = file.exists();
			// Check if file exist
			// If file doesn't exist, do nothing
			if (exists == true) {
				BufferedReader abc = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator
						+ "src" + File.separator + "Assets" + File.separator + "leaderboard.txt"));

				while ((line = abc.readLine()) != null) {
					lines.add(line.split(","));
				}
				abc.close();
			}

		} catch (IOException e) {
			System.out.println("Unable to Open File");
		}

		// Data to be displayed in the JTable
		data = lines.toArray(new String[][] {});

		// Initializing the JTable and display it
		JTable j = new JTable(data, columnNames);
		j.setFillsViewportHeight(true);
		j.setRowHeight(36);
		j.setIntercellSpacing(new Dimension(0, 0));
		Dimension tableSize = j.getPreferredSize();
		j.setBounds(268, 160, 400, 500);
		j.setOpaque(false);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setOpaque(false); // Set the renderer to transparent
		j.setDefaultRenderer(Object.class, render);
		render.setPreferredSize(new Dimension(0, 0));
		j.getTableHeader().setDefaultRenderer(render);
		j.setShowGrid(false);
		j.setEnabled(false);
		j.setFont(new Font("Tahoma", Font.PLAIN, 18));
		j.setForeground(Color.black);
		setColumnWidths(j, 450, 100, 50, 50);
		add(j);

		// Scoreboard background
		JLabel Score = new JLabel("New label");
		Score.setIcon(new ImageIcon(ScoreBoard.class.getResource("/Assets/board.jpg")));
		Score.setBounds(118, 0, 697, 579);
		add(Score);
		
		// Background image
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(699, 11, 74, 41);
		add(lblNewLabel_2);
		JLabel BACKGROUND = new JLabel();
		BACKGROUND.setIcon(new ImageIcon(ScoreBoard.class.getResource("/Assets/p0102gs2.jpg")));
		BACKGROUND.setBounds(0, 32, 945, 579);
		add(BACKGROUND);
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(ScoreBoard.class.getResource("/Assets/qfff.JPG")));
		lblNewLabel.setBounds(0, 0, 967, 52);
		add(lblNewLabel);
				
	}
	
	private void setColumnWidths(JTable table, int... widths) {
		/*
		 * Params : JTable table, int widths
		 * 
		 * Desc : Method for setting table width
		 * 
		 * Returns : None. 
		 */				
	    TableColumnModel columnModel = table.getColumnModel();
	    for (int i = 0; i < widths.length; i++) {
	        if (i < columnModel.getColumnCount()) {
	            columnModel.getColumn(i).setMaxWidth(widths[i]);
	        }
	        else break;
	    }
	}
}