package com.jumao.itms.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 坐标   & 比列
 * @author SongFei
 * @date 2015年8月6日
 */
public class Position extends JPanel implements ChangeListener {

	private static final long serialVersionUID = 1L;
	/** 横坐标 */
	private JSpinner xSpinner;
	/** 纵坐标 */
	private JSpinner ySpinner;
	/** 比例 */
	private JSpinner dSpinner;
	
	/** xSpinner需要的model */
	private SpinnerModel xModel;
	/** ySpinner需要的model */
	private SpinnerModel yModel;
	/** dSpinner需要的model */
	private SpinnerModel dModel;
	
	/** 设置spinner样式、布局 */
	private JFormattedTextField field;
	/** 画板对象 */
	private DrawingBoard board;
	
	public Position(DrawingBoard board) {
		this.board = board;
		setLayout(new GridLayout(3, 1, 5, 5));
		// 初始化界面
		initGUI();
	}
	
	private void initX() {
		xModel = new SpinnerNumberModel(board.currentShape.currentX, 8, 1500, 1);
		xSpinner = createSpinner(this, "X:", xModel);
		xSpinner.setEditor(new JSpinner.NumberEditor(xSpinner, "#"));
		xSpinner.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		xSpinner.addChangeListener(this);
		field = getTextField(xSpinner);
		if (field != null ) {
			field.setHorizontalAlignment(JTextField.RIGHT);
			field.setBorder(BorderFactory.createEmptyBorder(1,1,1,3));
		}
	}
	private void initY() {
		yModel = new SpinnerNumberModel(board.currentShape.currentY, 20, 850, 1);
		ySpinner = createSpinner(this, "Y:", yModel);
		ySpinner.setEditor(new JSpinner.NumberEditor(ySpinner, "#"));
		ySpinner.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		ySpinner.addChangeListener(this);
		field = getTextField(ySpinner);
		if (field != null ) {
			field.setHorizontalAlignment(JTextField.RIGHT);
			field.setBorder(BorderFactory.createEmptyBorder(1,1,1,3));
		}
	}
	private void initD() {
		dModel = new SpinnerNumberModel(board.currentShape.currentD, 0, 700, 1);
		dSpinner = createSpinner(this, "比例:", dModel);
		dSpinner.setEditor(new JSpinner.NumberEditor(dSpinner, "#"));
		dSpinner.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		dSpinner.addChangeListener(this);
		field = getTextField(dSpinner);
		if (field != null ) {
			field.setHorizontalAlignment(JTextField.RIGHT);
			field.setBorder(BorderFactory.createEmptyBorder(1,1,1,3));
		}
	}
	
	private void initGUI() {
//		// 参数： 初始值   最小值   最大值   增幅
//		xModel = new SpinnerNumberModel(board.currentShape.currentX, 8, 970, 1);
//		yModel = new SpinnerNumberModel(board.currentShape.currentY, 20, 520, 1);
//		dModel = new SpinnerNumberModel(board.currentShape.currentD, 0, 700, 1);
//		// 构建相关spinner
//		xSpinner = createSpinner(this, "X:", xModel);
//		ySpinner = createSpinner(this, "Y:", yModel);
//		dSpinner = createSpinner(this, "比例:", dModel);
//		// spinner文本框中的格式
//		xSpinner.setEditor(new JSpinner.NumberEditor(xSpinner, "#"));
//		ySpinner.setEditor(new JSpinner.NumberEditor(ySpinner, "#"));
//		dSpinner.setEditor(new JSpinner.NumberEditor(dSpinner, "#"));
//		// spinner边框
//		xSpinner.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//		ySpinner.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//		dSpinner.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//		// 添加监听
//		xSpinner.addChangeListener(this);
//		ySpinner.addChangeListener(this);
//		dSpinner.addChangeListener(this);
//		
//		field = getTextField(xSpinner);
//        if (field != null ) {
//            field.setHorizontalAlignment(JTextField.RIGHT);
//            field.setBorder(BorderFactory.createEmptyBorder(1,1,1,3));
//        }
//        field = getTextField(ySpinner);
//        if (field != null ) {
//            field.setHorizontalAlignment(JTextField.RIGHT);
//            field.setBorder(BorderFactory.createEmptyBorder(1,1,1,3));
//        }
//        field = getTextField(dSpinner);
//        if (field != null ) {
//            field.setHorizontalAlignment(JTextField.RIGHT);
//            field.setBorder(BorderFactory.createEmptyBorder(1,1,1,3));
//        }
		initX();
		initY();
		if (!"text".equals(board.currentShape.getType())) {
			initD();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		board.setCurrentX((Integer) xModel.getValue());
		board.setCurrentY((Integer) yModel.getValue());
		if (!"text".equals(board.currentShape.getType())) {
			board.setCurrentD((Integer) dModel.getValue());
		}
	}
	
	public JFormattedTextField getTextField(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            return ((JSpinner.DefaultEditor)editor).getTextField();
        } else {
            System.err.println("Unexpected editor type: " + spinner.getEditor().getClass() + " isn't a descendant of DefaultEditor");
            return null;
        }
    }
	
	/**
	 * 构建Spinner内容
	 * @param container 拥有者
	 * @param text 左侧文字
	 * @param model 右侧spinner需要的model
	 * @return
	 */
	private JSpinner createSpinner(Container container, String text, SpinnerModel model) {
		// 左侧文字（X、Y、比例）
		JLabel label = new JLabel(text);
		container.add(label);
		// 右侧spinner
		JSpinner spinner = new JSpinner(model);
//		label.setLabelFor(spinner);
		container.add(spinner);

		return spinner;
	}

}
