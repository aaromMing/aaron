package com.neusoft.chatroom.client.model;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class AreaField extends javax.swing.JFrame {
	private JTextField input;
	private JTextArea output;

	public static void main(String[] args) {
		AreaField inst = new AreaField();
		inst.setLocationRelativeTo(null);
		inst.setVisible(true);
	}

	public AreaField() {
		super();
		initGUI();
	}

	private void initGUI() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		{
			input = new JTextField();
			getContentPane().add(input, BorderLayout.NORTH);

			input.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					// 这句话就是让文本域中显示文本框内容
					output.setText("");
				}
			});
			PlainDocument doc = (PlainDocument) input.getDocument();
			doc.addDocumentListener(new DocumentListener() {

				public void changedUpdate(DocumentEvent e) {

				}

				public void insertUpdate(DocumentEvent e) {
					try {
						// 这里进行同步处理
						Document doc = e.getDocument();
						int offset = e.getOffset();
						String text = doc.getText(offset, e.getLength());
						Document adoc = output.getDocument();
						adoc.insertString(adoc.getLength(), text, null);
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
				}

				public void removeUpdate(DocumentEvent e) {

				}
			});
		}
		{
			output = new JTextArea();
			getContentPane().add(output, BorderLayout.CENTER);
			output.setLineWrap(true);
		}
		pack();
		setSize(400, 300);
	}

}