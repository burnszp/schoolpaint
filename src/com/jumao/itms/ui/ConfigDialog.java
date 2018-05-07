package com.jumao.itms.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 属性设置（针对还未开始画的）
 * 
 * @author SongFei
 * @date 2015年8月6日
 */
public class ConfigDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	// 主面板
	private JPanel content;
	// label
	private JLabel backLabel;
	private JLabel ovalLabel;
	private JLabel triaLabel;
	private JLabel lineLabel;
	// button
	private JButton backColor;
	private JButton ovalColor;
	private JButton triaColor;
	private JButton lineColor;
	// combox
	private JComboBox ovalSize;
	private JComboBox triaSize;
	private JComboBox lineSize;
	private JComboBox textFace;// 字体
	// button
	private JButton okButton;
	private JButton quitButton;
	
	private DrawingBoard board;
	
	/** 画笔大小集合 */
	private final String STROKES[] = new String[] { "10.0px", "8.0px", "6.5px", "4.0px", "2.5px", "2.0px","1.0px","0.5px" };
	/** 文字字体集合 */
//	private final String FACES[] = new String[] { "楷体", "隶书", "宋体", "华文彩云", "华文行楷", "微软雅黑" };
	/** 文字样式集合 */
//	private final String STYLE[] = new String[] { "常规", "粗体", "斜体" };
	/** 文字大小集合 */
	private final String SIZES[] = new String[] { "20", "22", "24", "26", "28", "30", "32", "34", "36", "38", "40", "42", "44", "46", "48", "50" };
	private JLabel brushPenLabel;
	private JButton brushPenColor;
	private JComboBox brushPenSize;

	// 全局字体
	private static final Font font = new Font("微软雅黑", Font.PLAIN, 12);

	public ConfigDialog(DrawingBoard board) {
		this.board = board;
		initGUI();
	}

	private void initGUI() {
		setModal(true);
		setSize(480, 300);
		setTitle("属性设置");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		content = new JPanel();
		content.setLayout(null);
		getContentPane().add(content);
		
		backLabel = new JLabel("背   景：");
		backLabel.setBounds(10, 10, 50, 25);
		backLabel.setFont(font);
		content.add(backLabel);
		
		backColor = new JButton();
		backColor.setBounds(85, 10, 80, 25);
		backColor.setBackground(board.getBackground());
		backColor.setPreferredSize(new Dimension(50, 25));
		backColor.addActionListener(this);
		content.add(backColor);

		ovalLabel = new JLabel("圆   形：");
		ovalLabel.setBounds(10, 55, 55, 25);
		ovalLabel.setFont(font);
		content.add(ovalLabel);

		ovalColor = new JButton();
		ovalColor.setBackground(board.oval_color);
		ovalColor.setBounds(85, 55, 80, 25);
		ovalColor.setPreferredSize(new Dimension(50, 25));
		ovalColor.addActionListener(this);
		content.add(ovalColor);

		ovalSize = new JComboBox();
		ovalSize.setBounds(190, 55, 80, 25);
		ovalSize.setFont(font);
		ovalSize.setModel(new DefaultComboBoxModel(STROKES));
		ovalSize.setSelectedItem(transToString(board.oval_stroke));
		ovalSize.addActionListener(this);
		content.add(ovalSize);

		triaLabel = new JLabel("三角形：");
		triaLabel.setBounds(10, 100, 55, 25);
		triaLabel.setFont(font);
		content.add(triaLabel);

		triaColor = new JButton();
		triaColor.setBackground(board.tria_color);
		triaColor.setBounds(85, 100, 80, 25);
		triaColor.setPreferredSize(new Dimension(50, 25));
		triaColor.addActionListener(this);
		content.add(triaColor);

		triaSize = new JComboBox();
		triaSize.setBounds(190, 100, 80, 25);
		triaSize.setFont(font);
		triaSize.setModel(new DefaultComboBoxModel(STROKES));
		triaSize.setSelectedItem(transToString(board.tria_stroke));
		triaSize.addActionListener(this);
		content.add(triaSize);

		lineLabel = new JLabel("线   条：");
		lineLabel.setBounds(10, 145, 55, 25);
		lineLabel.setFont(font);
		content.add(lineLabel);

		lineColor = new JButton();
		lineColor.setBounds(85, 145, 80, 25);
		lineColor.setBackground(board.line_color);
		lineColor.setPreferredSize(new Dimension(50, 25));
		lineColor.addActionListener(this);
		content.add(lineColor);

		lineSize = new JComboBox();
		lineSize.setBounds(190, 145, 80, 25);
		lineSize.setFont(font);
		lineSize.setModel(new DefaultComboBoxModel(STROKES));
		lineSize.setSelectedItem(transToString(board.line_stroke));
		lineSize.addActionListener(this);
		content.add(lineSize);
		
		brushPenLabel = new JLabel("毛 笔");
		brushPenLabel.setBounds(10, 190, 55, 25);
		brushPenLabel.setFont(font);
		content.add(brushPenLabel);

		brushPenColor = new JButton();
		brushPenColor.setBounds(85, 190, 80, 25);
		brushPenColor.setBackground(board.brushPen_color);
		brushPenColor.setPreferredSize(new Dimension(50, 25));
		brushPenColor.addActionListener(this);
		content.add(brushPenColor);

		brushPenSize = new JComboBox();
		brushPenSize.setBounds(190, 190, 80, 25);
		brushPenSize.setFont(font);
		brushPenSize.setModel(new DefaultComboBoxModel(STROKES));
		brushPenSize.setSelectedItem(transToString(board.brushPen_stroke));
		brushPenSize.addActionListener(this);
//		brushPenSize.setEditable(true);
		content.add(brushPenSize);






		okButton = new JButton("确定");
		okButton.setBounds(85, 235, 80, 30);
		okButton.setFont(font);
		okButton.addActionListener(this);
		content.add(okButton);

		quitButton = new JButton("取消");
		quitButton.setBounds(290, 235, 80, 30);
		quitButton.setFont(font);
		quitButton.addActionListener(this);
		content.add(quitButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backColor) {
			Color color = JColorChooser.showDialog(this, "选择画板背景", board.getBackground());
			backColor.setBackground(color);
		}
		if (e.getSource() == ovalColor) {
			Color color = JColorChooser.showDialog(this, "选择画笔颜色", board.oval_color);
			ovalColor.setBackground(color);
		}
		if (e.getSource() == triaColor) {
			Color color = JColorChooser.showDialog(this, "选择画笔颜色", board.tria_color);
			triaColor.setBackground(color);
		}
		if (e.getSource() == lineColor) {
			Color color = JColorChooser.showDialog(this, "选择画笔颜色", board.line_color);
			lineColor.setBackground(color);
		}
		if (e.getSource() == brushPenColor) {
			Color color = JColorChooser.showDialog(this, "选择画笔颜色", board.brushPen_color);
			brushPenColor.setBackground(color);
		}
		if (e.getSource() == okButton) {
			// color
			board.setBackground(backColor.getBackground());
			board.oval_color = ovalColor.getBackground();
			board.tria_color = triaColor.getBackground();
			board.line_color = lineColor.getBackground();
			board.brushPen_color = brushPenColor.getBackground();
			// size
			board.oval_stroke = transToFloat(ovalSize);
			board.tria_stroke = transToFloat(triaSize);
			board.line_stroke = transToFloat(lineSize);
			board.brushPen_stroke = transToFloat(brushPenSize);
			// text特殊处理
			// close
			dispose();
		}
		if (e.getSource() == quitButton) {
			dispose();
		}
	}

	/**
	 * 将float转成string （px） 只针对stroke之间转换，不共用
	 * 
	 * @param value
	 * @return
	 */
	private String transToString(float value) {
		String temp = String.valueOf(value);
		return temp + "px";
	}

	/**
	 * 将string转成float 只针对stroke之间转换，不共用
	 * 
	 * @param object
	 *            jcombox对象
	 * @return
	 */
	private float transToFloat(Object object) {
		String temp = ((JComboBox) object).getSelectedItem().toString();
		return Float.parseFloat(temp.substring(0, temp.length() - 2));
	}

	/**
	 * 根据字体编码获取名称
	 * 
	 * @param style
	 *            字体编码
	 * @return
	 */
	private String getStyleStr(int style) {
		if (style == Font.PLAIN) {
			return "常规";
		}
		if (style == Font.BOLD) {
			return "粗体";
		}
		if (style == Font.ITALIC) {
			return "斜体";
		}
		return null;
	}

}
