
package notepad;

import com.sun.org.apache.xml.internal.serialize.Method;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.DefaultEditorKit;



class EditorTab
{
    File file;
    JTextArea jt;

    public EditorTab(JTextArea jt, File file) 
    {
        this.file=file;
        this.jt=jt;
    }
    
}


public class Home implements ActionListener, CaretListener
{
    JFrame jf;
    JMenuBar bar;
    JMenu filee, edit, view, zoom;
    JMenuItem ntab, window, markdown, open, recent, save, saveas, saveall, pagesetup, print, closetab, closewindow, exit, zin, zout, zdef, cut, copy, paste;
    JCheckBoxMenuItem wrap,  status;
    JFileChooser fileChooser;
    JTabbedPane tabs;
    
    HashMap<Component, EditorTab> tabMap = new HashMap<>();
    
    
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
        
        
        edit=new JMenu("Edit");
        cut=new JMenuItem(new DefaultEditorKit.CutAction());
        cut.setText("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, shortcut));
//        cut.addActionListener(this);

        copy=new JMenuItem(new DefaultEditorKit.CopyAction());
        copy.setText("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, shortcut));
        
        paste=new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, shortcut));
        paste.addActionListener(this);
        
        edit.add(cut);
        edit.add(paste);
        edit.add(copy);
        
        
        bar.add(filee);
        bar.add(edit);
        bar.add(view);
        
        
        
        tabs=new JTabbedPane();
        tabs.addChangeListener(e -> {updateCutCopy();});
        jf.add(tabs);
        
        addNewTab(null);
        
        
        jf.setJMenuBar(bar);
        jf.setVisible(true);
    }

    
    public void addNewTab(File file)
    {
        JTextArea ja=new JTextArea();
        ja.addCaretListener(this);
        cut.setEnabled(false);
        copy.setEnabled(false);
        ja.setFont(new Font("Consolas", Font.PLAIN, 16));
        JScrollPane scroll=new JScrollPane(ja);
        String title = (file == null) ? "Untitled" : file.getName();
        
        tabs.addTab(title, scroll);
         
        tabs.setSelectedComponent(scroll);
        tabMap.put(scroll, new EditorTab(ja, file));
    }
    
    
    @Override 
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==save)
        {
            saveAs();
        }
        if(ae.getSource()==open)
        {
            open();
        }
        if(ae.getSource()==ntab)
        {
            addNewTab(null);
        }
    }
    
    
    public void saveAs()
    {
        EditorTab ed=tabMap.get(tabs.getSelectedComponent());
        String text=ed.jt.getText();
        if(!text.isEmpty())
        {
            fileChooser = new JFileChooser();
            if(fileChooser.showSaveDialog(jf)==0)
            {
                File file=fileChooser.getSelectedFile();
                try (FileOutputStream fos=new FileOutputStream(file);)
                {
                    fos.write(text.getBytes());
                    ed.file=file;
                    int index=tabs.getSelectedIndex();
                    tabs.setTitleAt(index, file.getName());
                }
                catch (IOException e)
                {
                    System.err.println("File save error: "+e);
                }
                 
            }
            else
            {
                JOptionPane.showMessageDialog(jf, "File not saved!", "File Not Saved", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    public void open()
    {
        fileChooser = new JFileChooser();
        if(fileChooser.showSaveDialog(jf)==0)
        {
            File file=fileChooser.getSelectedFile();
            try(FileReader fr=new FileReader(file))
            {
                EditorTab ed=tabMap.get(tabs.getSelectedComponent());
                ed.file=file;
                ed.jt.read(fr, null);
                tabs.setTitleAt(tabs.getSelectedIndex(), file.getName());
            }
            catch(IOException e)
            {
                System.err.println("File open error: "+e);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(jf, "File not opened!", "Operation Cancelled", JOptionPane.WARNING_MESSAGE);
        }
        
    }

    
    void updateCutCopy()
    {
        EditorTab ed=tabMap.get(tabs.getSelectedComponent());
        if(ed==null)
        {
        }
        else
        {
            JTextArea ja=ed.jt;
            boolean hasSelected= ja.getSelectionStart() != ja.getSelectionEnd();
            cut.setEnabled(hasSelected);
            copy.setEnabled(hasSelected);  
        }
        
    }
  
    
    public void caretUpdate(CaretEvent ce) {
        updateCutCopy();
    }
}
