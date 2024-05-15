import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TextEditor implements ActionListener {
    //declaring properties of text editor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    JMenuItem newFile, openFile, saveFile, closeFile;
    JMenuItem cut, copy, paste, selectAll;
    JTextArea textArea;
    TextEditor(){
        //initializing a frame
        frame = new JFrame();
        //initialize menu bar
        menuBar = new JMenuBar();
        //initializing text area
        textArea = new JTextArea();
        //initializing menu
        file = new JMenu("File");
        edit = new JMenu("Edit");
        //initializing menu items
        newFile = new JMenuItem("New file");
        openFile = new JMenuItem("Open file");
        saveFile = new JMenuItem("Save");
        closeFile = new JMenuItem("Close");
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select all");
        //add action listeners to the menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        closeFile.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        //add menu items
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(closeFile);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        //add menus
        menuBar.add(file);
        menuBar.add(edit);
        //creat content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(2, 2, 2, 2));
        panel.setLayout(new BorderLayout(0, 0));
        //add text area to panel
        panel.add(textArea, BorderLayout.CENTER);
        //creat scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);
        //set menu bar
        frame.setJMenuBar(menuBar);
        //set boundaries for the frame
        frame.setBounds(200, 200, 400, 400);
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource() == cut){
            //perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource() == copy){
            //perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource() == paste){
            //perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll){
            //perform select all operation
            textArea.selectAll();
        }
        if(actionEvent.getSource() == openFile){
            //perform open file operation
            //open file chooser
            JFileChooser fileChooser = new JFileChooser("E:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //if we click open button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //getting selected file
                File file = fileChooser.getSelectedFile();
                //getting the path of the selected file
                String filePath = file.getPath();
                // try and catch function
                try{
                    //initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //initialize buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    while((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate + "\n";
                    }
                    textArea.setText(output);
                    bufferedReader.close();
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == saveFile){
            //perform save file operation
            //initialize file picker/ chooser
            JFileChooser fileChooser = new JFileChooser("E:");
            int chooserOption = fileChooser.showSaveDialog(null);
            //if we select save button
            if(chooserOption == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                try{
                    //initialize file write
                    FileWriter fileWriter = new FileWriter(file);
                    //initialize buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write content of the text area to a file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == newFile){
            TextEditor newTextEditor = new TextEditor();
        }
        if(actionEvent.getSource() == closeFile){
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}