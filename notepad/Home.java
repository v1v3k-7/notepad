
package notepad;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;


public class Home {
    JFrame jf;
    JMenuBar bar;
    JMenu filee, edit, view, zoom;
    JMenuItem ntab, window, markdown, open, recent, save, saveas, saveall, pagesetup, print, closetab, closewindow, exit, zin, zout, zdef;
    JCheckBoxMenuItem wrap,  status;
    
    public Home()
    {
        jf=new JFrame();
        jf.setSize(1000, 700);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        try
        {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
              UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            
        } catch (Exception e)
        {
            System.out.println("System look and feel error: "+ e);
        }
        
        bar=new JMenuBar();
        
        filee=new JMenu("File");
        
        int shortcut=Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
        
        ntab=new JMenuItem("New tab");
        ntab.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, shortcut));
        filee.add(ntab);
        window=new JMenuItem("New window");
        window.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, shortcut | KeyEvent.SHIFT_DOWN_MASK));
        filee.add(window);
        markdown=new JMenuItem("New Markdown tab");
        filee.add(markdown);
        open=new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, shortcut));
        filee.add(open);
        recent=new JMenuItem("Recent");
        filee.add(recent);
        save=new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, shortcut));
        filee.add(save);
        saveas=new JMenuItem("Save as");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, shortcut|KeyEvent.SHIFT_DOWN_MASK));
        filee.add(saveas);
        saveall=new JMenuItem("Save all");
        saveall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, shortcut|KeyEvent.ALT_DOWN_MASK));
        filee.add(saveall);
        filee.addSeparator();
        pagesetup=new JMenuItem("Page Setus");
        filee.add(pagesetup);
        print=new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, shortcut));
        filee.add(print);
        filee.addSeparator();
        closetab=new JMenuItem("Close tab");
        closetab.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, shortcut));
        filee.add(closetab);
        closewindow=new JMenuItem("Close window");
        closewindow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, shortcut|KeyEvent.SHIFT_DOWN_MASK));
        filee.add(closewindow);
        exit=new JMenuItem("Exit");
        filee.add(exit);
        
        
        view=new JMenu("View");
        zoom=new JMenu("Zoom");
        zin=new JMenuItem("Zoom in");
        zin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, shortcut));
        zoom.add(zin);
        zout=new JMenuItem("Zoom out");
        zout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, shortcut));
        zoom.add(zout);
        zdef=new JMenuItem("Restore defalt zoom");
        zdef.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, shortcut));
        zoom.add(zdef);
        
        view.add(zoom);
        
        status=new JCheckBoxMenuItem("Status bar");
        wrap=new JCheckBoxMenuItem("Word wrap");
        view.add(status);
        view.add(wrap);
        
        bar.add(filee);
        bar.add(view);
        
        jf.setJMenuBar(bar);
        jf.setVisible(true);
    }
}
