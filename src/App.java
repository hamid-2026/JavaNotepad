
import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.BorderLayout;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPopupMenu.Separator;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class App extends JFrame {
    JTextArea txt = new JTextArea();


    public App(){

        this.setTitle("Notepad");
        this.setSize(1000,600);
        this.getContentPane().setBackground(Color.lightGray);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        var width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        var height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        int x = (int)(width-1000)/2;
        int y = (int)(height-600)/2;

        this.setLocation(x,y);
        this.setLayout(new BorderLayout(2,2));

        ScrollPane scr1 = new ScrollPane();

        scr1.setBounds(2, 2, 980, 555);
        this.add(scr1);
        scr1.add(txt);
 
        JMenuBar mBar = new JMenuBar();
        this.setJMenuBar(mBar);

        JCheckBox chk1 = new JCheckBox("Stutes Bar");

        Separator sp1 = new Separator();
        Separator sp2 = new Separator();
        Separator sp3 = new Separator();
        Separator sp4 = new Separator();
        Separator sp5 = new Separator();
        Separator sp6 = new Separator();
        
        JMenu file = new JMenu();
        JMenu edit = new JMenu();
        JMenu format = new JMenu();
        JMenu view = new JMenu();
        JMenu help = new JMenu();

        JMenuItem fnew = new JMenuItem();
        JMenuItem fopen = new JMenuItem();
        JMenuItem fsave = new JMenuItem();
        JMenuItem fsaveAs = new JMenuItem();
        JMenuItem fpageSetup = new JMenuItem();
        JMenuItem fprint = new JMenuItem();
        JMenuItem fexit = new JMenuItem();

        JMenuItem Eundo = new JMenuItem();
        JMenuItem Ecut = new JMenuItem();
        JMenuItem Ecopy = new JMenuItem();
        JMenuItem Epaste = new JMenuItem();
        JMenuItem Edelet = new JMenuItem();
        JMenuItem Efind = new JMenuItem();
        JMenuItem EfindNext = new JMenuItem();
        JMenuItem Ereplace = new JMenuItem();
        JMenuItem EgoTo = new JMenuItem();
        JMenuItem EselectAll = new JMenuItem();
        JMenuItem EtimeDate = new JMenuItem();

        JMenuItem FwordWrap = new JMenuItem();
        JMenuItem Ffont = new JMenuItem();

        JMenuItem Hhelp = new JMenuItem();
        JMenuItem HaboutMe = new JMenuItem();

        file.setText("File");
        edit.setText("Edit");
        format.setText("Format");
        view.setText("View");
        help.setText("Help");

        fnew.setText("New");
        fopen.setText("Open");
        fsave.setText("Save");
        fsaveAs.setText("Save as");
        fpageSetup.setText("Page setup");
        fprint.setText("Print");
        fexit.setText("Exit");

        Eundo.setText("Undo");
        Ecut.setText("Cut");
        Ecopy.setText("Copy");
        Epaste.setText("Paste");
        Edelet.setText("Delet");
        Efind.setText("Find");
        EfindNext.setText("Find next");
        Ereplace.setText("Replace");
        EgoTo.setText("Go To");
        EselectAll.setText("Select all");
        EtimeDate.setText("Time/Date");

        FwordWrap.setText("Word Wrap");
        Ffont.setText("Font...");

        Hhelp.setText("Help");
        HaboutMe.setText("About Notepad");

        file.add(fnew);
        file.add(fopen);
        file.add(fsave);
        file.add(fsaveAs);
        file.add(sp1);
        file.add(fpageSetup);
        file.add(fprint);
        file.add(sp2);
        file.add(fexit);

        edit.add(Eundo);
        edit.add(sp3);
        edit.add(Ecut);
        edit.add(Ecopy);
        edit.add(Epaste);
        edit.add(Edelet);
        edit.add(sp4);
        edit.add(Efind);
        edit.add(EfindNext);
        edit.add(Ereplace);
        edit.add(EgoTo);
        edit.add(sp5);
        edit.add(EselectAll);
        edit.add(EtimeDate);

        format.add(FwordWrap);
        format.add(Ffont);

        view.add(chk1);

        help.add(Hhelp);
        help.add(sp6);
        help.add(HaboutMe);

        mBar.add(file);
        mBar.add(edit);
        //mBar.add(format);
        mBar.add(view);
        mBar.add(help);

        //shortcuts
        fnew.setAccelerator(KeyStroke.getKeyStroke("control N"));
        fopen.setAccelerator(KeyStroke.getKeyStroke("control O"));
        fsave.setAccelerator(KeyStroke.getKeyStroke("control S"));
        fsaveAs.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
        fprint.setAccelerator(KeyStroke.getKeyStroke("control P"));

        Eundo.setAccelerator(KeyStroke.getKeyStroke("control Z"));
        Ecopy.setAccelerator(KeyStroke.getKeyStroke("control C"));
        Ecut.setAccelerator(KeyStroke.getKeyStroke("control X"));
        Epaste.setAccelerator(KeyStroke.getKeyStroke("control V"));
        EselectAll.setAccelerator(KeyStroke.getKeyStroke("control A"));
        
        Eundo.setEnabled(false);
        Ecut.setEnabled(false);
        Ecopy.setEnabled(false);
        Edelet.setEnabled(false);
        Efind.setEnabled(false);
        EfindNext.setEnabled(false);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                String text = txt.getText().trim();
                if(text.isEmpty()){
                    System.exit(0);
                }
                else{
                int option = javax.swing.JOptionPane.showOptionDialog(App.this,"Do you want to save?","NotePad",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,null,JOptionPane.INFORMATION_MESSAGE);
                    if(option==JOptionPane.YES_OPTION){
                        saveFile();
                        System.exit(0);
                    }
                    else if(option==JOptionPane.NO_OPTION){
                        System.exit(0);
                    }
                    else if(option == JOptionPane.CANCEL_OPTION){
                        App.this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    }
                }   
            }
        });

        fsave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });
        fsaveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                saveAsFile();
            }
        });
        fopen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                openFile();
            }
        });
        fnew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                newFile();
            }
        });
        fexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                exitFile();
            }
        });
        txt.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e){
                Eundo.setEnabled(true);
                Ecut.setEnabled(true);
                Ecopy.setEnabled(true);
                Edelet.setEnabled(true);
                Efind.setEnabled(true);
                EfindNext.setEnabled(true);
            }
        });
        //Edit MenuItem
        // Undo
        UndoManager undo = new UndoManager();
        txt.getDocument().addUndoableEditListener(undo);

        Eundo.addActionListener(new ActionListener() {
            @Override
           public void actionPerformed(ActionEvent e){
                try{
                    if(undo.canUndo()){
                        undo.undo();
                    }
                }catch(CannotUndoException ex){
                    javax.swing.JOptionPane.showMessageDialog(App.this,"There is no thing for undo!!");
                }
           } 
        });
        Ecut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                txt.cut();
            }
        });
        Ecopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                txt.copy();
            }
        });
        Epaste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                txt.paste();
            }
        });
        Edelet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                txt.replaceSelection("");
                
            }
        });
        Hhelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(App.this,"If you have any question you can share with me  \n My Email :hamidjaffari21@gmail.com","Help",JOptionPane.PLAIN_MESSAGE);
            }
        });
        HaboutMe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(App.this,"This app devloped by Hamid Jaffari "+"\n"+" Email : hamidjaffari22@gmail.com","Devloper",JOptionPane.PLAIN_MESSAGE);
            }
        });
        
    }
        // save file 
        private void saveFile(){
            JFileChooser fileChooser = new JFileChooser();
        
            int option = fileChooser.showSaveDialog(this);
            if(option==JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                //  اگر همنام فایل پیدا نشد مستقیم سیو شود
                if(file.exists()==false){ 
                    try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
                        String[] lines = txt.getText().split("\n");
                        for(String line : lines){
                            writer.write(line);
                            writer.newLine();
                        } 
                        writer.write(System.lineSeparator());
                        javax.swing.JOptionPane.showMessageDialog(this,"File saved Successfuly");
                    }catch(IOException ex){
                        javax.swing.JOptionPane.showMessageDialog(this,"Error saving File");
                    }
                }
                //اگر همنام فایل پیدا شد از کاربر بخواهد مه میخواهد دوباره نوشته شود یا خیر
                else if(file.exists()==true){
                    int save = javax.swing.JOptionPane.showConfirmDialog(App.this,"Do you want to rewrite file?");
                    if(save==JOptionPane.YES_OPTION){// دوباره سیو شود
                        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
                            String[] lines = txt.getText().split("\n");
                            for(String line : lines){
                            writer.write(line);
                            writer.newLine();
                            } 
                            writer.write(System.lineSeparator());
                            javax.swing.JOptionPane.showMessageDialog(this,"File saved Successfuly");
                        }catch(IOException ex){
                            javax.swing.JOptionPane.showMessageDialog(this,"Error saving File");
                        }     
                    }
                    //اگر روی نخیر زد دوباره مسیر سیو فایل نمایش داده شود
                    else if(save==JOptionPane.NO_OPTION){
                        JFileChooser fileChooser2 = new JFileChooser();
        
                        int option2 = fileChooser.showSaveDialog(this);
                        if(option2==JFileChooser.APPROVE_OPTION){
                            File file2 = fileChooser2.getSelectedFile();
                            
                            try(BufferedWriter writer = new BufferedWriter(new FileWriter(file2))){
                                String[] lines = txt.getText().split("\n");
                                for(String line : lines){
                                    writer.write(line);
                                    writer.newLine();
                                } 
                                writer.write(System.lineSeparator());
                                javax.swing.JOptionPane.showMessageDialog(this,"File saved Successfuly");
                            }catch(IOException ex){
                                javax.swing.JOptionPane.showMessageDialog(this,"Error saving File");
                            } 
                        }
                
                    }
                }    
            }
        }
        private void saveAsFile(){
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(this);

            if(option==JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
                    String[] lines=txt.getText().split("\n");
                    for(String line:lines){
                        writer.write(line);
                        writer.newLine();
                    }
                    writer.write(System.lineSeparator());
                    javax.swing.JOptionPane.showMessageDialog(this,"File saved Successfuly");
                }catch(IOException ex){
                    javax.swing.JOptionPane.showMessageDialog(this,"Error saving File");
                }
            }
        }
        private void openFile(){
            JFileChooser fileChooser2 = new JFileChooser();

            int option = fileChooser2.showOpenDialog(this);
            if(option==JFileChooser.APPROVE_OPTION){
                File file2 = fileChooser2.getSelectedFile();
                try(BufferedReader reader = new BufferedReader(new FileReader(file2))){
                    txt.setText("");

                    String line;
                    while ((line = reader.readLine())!=null){
                        txt.append( line +"\n");
                    }

                }catch(IOException e){
                    javax.swing.JOptionPane.showMessageDialog(this,"Error in Opening file");
                }
            }

        }
        private void newFile(){
            int option = javax.swing.JOptionPane.showOptionDialog(this,"Do you want to save?","NotePad",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,null,JOptionPane.PLAIN_MESSAGE);

            if(option==JOptionPane.YES_OPTION){
                saveFile();
                txt.setText("");
            }
            else if(option == JOptionPane.NO_OPTION){
                txt.setText("");
            } 
        }
        public void exitFile(){
            int option = javax.swing.JOptionPane.showOptionDialog(this,"Do you want to save?","NotePad",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,null,null);
            if(option==JOptionPane.YES_OPTION){
                saveFile();
                System.exit(0);
            }
            else if(option==JOptionPane.NO_OPTION){
                System.exit(0);
            }
        }
      
       
    public static void main(String[] args) throws Exception {

        //Look and Feel
        try{
            for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
                if("Nimbus".equals(info.getClassName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(UnsupportedLookAndFeelException e){
            System.out.println("not support");
        }catch(ClassNotFoundException e){
            System.out.println("Class not found");
        }catch(InstantiationException e){
            System.out.println("Not insrall");
        }catch(IllegalAccessException e){
            System.out.println("Illega");
        }

        new App().setVisible(true);


    }

}
