
package notepad;

import com.sun.org.apache.xml.internal.serialize.Method;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;


public class Home implements ActionListener
{
    JFrame jf;
    JMenuBar bar;
    JMenu filee, edit, view, zoom;
    JMenuItem ntab, window, markdown, open, recent, save, saveas, saveall, pagesetup, print, closetab, closewindow, exit, zin, zout, zdef;
    JCheckBoxMenuItem wrap,  status;
    JTextArea jt;
    JFileChooser fileChooser;
    
    public Home()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            
        } catch (Exception e)
        {
            System.out.println("System look and feel error: "+ e);
        }
        
        jf=new JFrame();
        jf.setSize(1000, 700);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        
        
        bar=new JMenuBar();
        
        filee=new JMenu("File");
        
        int shortcut=Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
        
        ntab=new JMenuItem("New tab");
        ntab.addActionListener(this);
        ntab.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, shortcut));
        filee.add(ntab);
        window=new JMenuItem("New window");
        window.addActionListener(this);
        window.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, shortcut | KeyEvent.SHIFT_DOWN_MASK));
        filee.add(window);
        markdown=new JMenuItem("New Markdown tab");
        markdown.addActionListener(this);
        filee.add(markdown);
        open=new JMenuItem("Open");
        open.addActionListener(this);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, shortcut));
        filee.add(open);
        recent=new JMenuItem("Recent");
        recent.addActionListener(this);
        filee.add(recent);
        save=new JMenuItem("Save");
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, shortcut));
        filee.add(save);
        saveas=new JMenuItem("Save as");
        saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, shortcut|KeyEvent.SHIFT_DOWN_MASK));
        saveas.addActionListener(this);
        filee.add(saveas);
        saveall=new JMenuItem("Save all");
        saveall.addActionListener(this);
        saveall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, shortcut|KeyEvent.ALT_DOWN_MASK));
        filee.add(saveall);
        filee.addSeparator();
        pagesetup=new JMenuItem("Page Setus");
        pagesetup.addActionListener(this);
        filee.add(pagesetup);
        print=new JMenuItem("Print");
        print.addActionListener(this);
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, shortcut));
        filee.add(print);
        filee.addSeparator();
        closetab=new JMenuItem("Close tab");
        closetab.addActionListener(this);
        closetab.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, shortcut));
        filee.add(closetab);
        closewindow=new JMenuItem("Close window");
        closewindow.addActionListener(this);
        closewindow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, shortcut|KeyEvent.SHIFT_DOWN_MASK));
        filee.add(closewindow);
        exit=new JMenuItem("Exit");
        filee.add(exit);
        
        
        view=new JMenu("View");
        zoom=new JMenu("Zoom");
        zin=new JMenuItem("Zoom in");
        zin.addActionListener(this);
        zin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, shortcut));
        zoom.add(zin);
        zout=new JMenuItem("Zoom out");
        zout.addActionListener(this);
        zout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, shortcut));
        zoom.add(zout);
        zdef=new JMenuItem("Restore defalt zoom");
        zdef.addActionListener(this);
        zdef.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, shortcut));
        zoom.add(zdef);
        
        view.add(zoom);
        
        status=new JCheckBoxMenuItem("Status bar");
        status.addActionListener(this);
        wrap=new JCheckBoxMenuItem("Word wrap");
        wrap.addActionListener(this);
        view.add(status);
        view.add(wrap);
        
        bar.add(filee);
        bar.add(view);
        
        
        jt=new JTextArea();
        jt.setFont(new Font("Consolas", Font.PLAIN, 16));
        
        JScrollPane scroll=new JScrollPane(jt);
        
        jf.add(scroll);
        jf.setJMenuBar(bar);
        jf.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==save)
        {
            save();
        }
    }
    
    public void save()
    {
        String text=jt.getText();
        if(!text.isEmpty())
        {
            fileChooser = new JFileChooser();
            if(fileChooser.showSaveDialog(jf)==0)
            {
                
            }
            else
            {
                JOptionPane.showMessageDialog(jf, "File not saved!", "File Not Saved", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
