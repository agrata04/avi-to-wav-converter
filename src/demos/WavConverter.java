package demos;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.VideoAttributes;
import it.sauronsoftware.jave.VideoSize;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;




public class WavConverter implements ActionListener {
	
	JFrame frame;
	JButton browse1,browse2,convert;
	
	JPanel p1,p2;
	JTextField t1,t2;
	
	public WavConverter()
	{
		frame = new JFrame("Avi to Flv");
		browse1 = new JButton("Browse");
		browse2 = new JButton("Browse");
		convert = new JButton("Convert");
		
		p1 = new JPanel();
		p2 = new JPanel();
		
		t1 = new JTextField(20);
		t2 = new JTextField(20);
		
		add_to();
	}
	
	public static void main(String[] args)
	{
		new WavConverter();
	}
	
	public void add_to()
	{
		p1.add(browse1);
		p1.add(t1);
		
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		p2.add(browse2);
		p2.add(t2);
		
		p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		p1.setBorder(new TitledBorder("Avi File"));
		p2.setBorder(new TitledBorder("Flv File"));
		
		
		frame.add(p1,BorderLayout.NORTH);
		frame.add(p2,BorderLayout.CENTER);
		frame.add(convert,BorderLayout.SOUTH);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,400);
		
		browse1.setActionCommand("Browse1");
		browse2.setActionCommand("Browse2");
		convert.setActionCommand("Convert");
	
		
		browse1.addActionListener(this);
		browse2.addActionListener(this);
		convert.addActionListener(this);
		
		frame.validate();
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String input = t1.getText();
		String output = t2.getText();
		
		String s = e.getActionCommand();
		
		if(s.equals("Browse1"))
		{
			JFrame f = new JFrame("Select Location");
			JFileChooser fc = new JFileChooser();
			f.add(fc);
			f.setVisible(true);
			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			
			int returnval = fc.showDialog(f,"select");
			if(returnval == JFileChooser.APPROVE_OPTION) {
			    fc.approveSelection();
			   // f.pack();
			    //f.setLocation(0,0);
			    t1.setText(fc.getCurrentDirectory().getPath()+File.separator+fc.getSelectedFile().getName());
				
			    f.setVisible(false);
			    f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				
			}
			else if(returnval == JFileChooser.CANCEL_OPTION){
				fc.cancelSelection();
				t1.setText("");
				f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				
			}
			else{
				
			}
		}else if(s.equals("Browse2"))
		{
			JFrame f = new JFrame("Select Location");
			JFileChooser fc = new JFileChooser();
			f.add(fc);
			f.setVisible(true);
			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			
			int returnval = fc.showDialog(f,"select");
			if(returnval == JFileChooser.APPROVE_OPTION) {
			    fc.approveSelection();
			   // f.pack();
			    //f.setLocation(0,0);
			    t2.setText(fc.getCurrentDirectory().getPath()+File.separator+fc.getSelectedFile().getName());
				
			    f.setVisible(false);
			    f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				
			}
			else if(returnval == JFileChooser.CANCEL_OPTION){
				fc.cancelSelection();
				t2.setText("");
				f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				
			}
			else{
				
			}
		}else if(s.equals("Convert")){
		
			
			File source = new File(t1.getText());
			File target = new File(t2.getText());
			AudioAttributes audio = new AudioAttributes();
			audio.setCodec("pcm_s16le");
			EncodingAttributes attrs = new EncodingAttributes();
			attrs.setFormat("wav");
			attrs.setAudioAttributes(audio);
			Encoder encoder = new Encoder();
			
			try{
				encoder.encode(source, target, attrs);
				
			} catch (InputFormatException e12) {
				// TODO Auto-generated catch block
				e12.printStackTrace();
			} catch (EncoderException e123) {
				// TODO Auto-generated catch block
				e123.printStackTrace();
			}
			
				
			
		}
		else{
			
		}
		
	}
	
	public JDialog mydia(String lab)
	{
		final JDialog d = new JDialog();
		d.add(new JLabel(lab));
		JButton b = new JButton("OK");
		ActionListener al = new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				d.setVisible(false);
				
			}
			
		};
		d.add(b);
		
		return d;
		
	}
	

}
