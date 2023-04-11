import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class myFrame extends JFrame{
    private JButton clearButton;
    private JButton startButton;
    private static HashMap<Node,int[]> nodes;
    
    public myFrame() {
        super("DIJKSTRA'S ALGORITHM");
        setBackground(Color.decode("#314259"));
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Whiteboard whiteboard = new Whiteboard();
        whiteboard.setPreferredSize(new Dimension(1000, 500));
        add(whiteboard, BorderLayout.NORTH);

        nodes = whiteboard.getNodes();

        JPanel buttonPanel = new JPanel();
        JTextArea outputArea = new JTextArea();
        buttonPanel.setLayout(new GridLayout(1, 2));

        clearButton = new JButton("CLEAR");
        clearButton.setFont(new Font("Arial", Font.BOLD, 15));
        clearButton.setForeground(Color.decode("#314259"));
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                whiteboard.reset();
                nodes.clear();
                outputArea.setText("");
            }
        });
        buttonPanel.add(clearButton);

        startButton = new JButton("START");
        startButton.setFont(new Font("Arial", Font.BOLD, 15));
        startButton.setForeground(Color.decode("#314259"));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DijkstraAlgo DA = new DijkstraAlgo();
                String output = "";
                for(Node i : nodes.keySet()){
                    if(i.getId() == 'A'){
                        output = DA.dijkstra(i);
                    }
                }
                outputArea.setText(output);

                System.out.println(output);
            }
        });
        buttonPanel.add(startButton);

        
        
        buttonPanel.setBackground(Color.decode("#314259"));
        buttonPanel.setPreferredSize(new Dimension(1000, 100));
        add(buttonPanel, BorderLayout.CENTER);

        outputArea.setMargin(new Insets(10, 10, 0, 0));
        outputArea.setEditable(false);
        outputArea.setPreferredSize(new Dimension(900, 200));
        outputArea.setBackground(Color.decode("#77A1D9"));
        outputArea.setForeground(Color.BLACK);
        outputArea.setFont(new Font("Arial", Font.BOLD, 15));
        JScrollPane scrollPane = new JScrollPane(outputArea); // add a scroll bar to the JTextArea
        add(scrollPane, BorderLayout.SOUTH);

        setVisible(true);
    }
}
