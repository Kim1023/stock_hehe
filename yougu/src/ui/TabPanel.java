package ui;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class TabPanel extends JPanel {
	
	private JLabel title;
    private CloseButton closebutton;
    private JTabbedPane pane;
 
    public TabPanel(String s,JTabbedPane pane){
    	super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        title=new JLabel(s);
        this.pane=pane;
        closebutton=new CloseButton();
        add(title);
        add(closebutton);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setOpaque(false);
    }
 
    private class CloseButton extends JButton {
        private ImageIcon icon;
        public CloseButton(){
            icon=new ImageIcon(getClass().getResource("/Image/close.jpg"));
            setSize(icon.getImage().getWidth(null),icon.getImage().getHeight(null));
            setIcon(icon);
            setBorder(null);
            setBorderPainted(false);
            setFocusPainted(false);
            addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e){
                    pane.remove(pane.indexOfTabComponent(TabPanel.this));
                    if (pane.getTabCount()==0) {
                        pane.addTab("blank", null);
                    }
                }
            });
        }
    }
}
