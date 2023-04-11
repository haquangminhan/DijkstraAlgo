import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Whiteboard extends JPanel {
    private static Node fromNode, toNode;
    private static char firstChar = 'A';
    private int lastX = -1;
    private int lastY = -1;
    private static HashMap<Node,int[]> center = new HashMap<>();
    private int circleRadius = 20;  
    // private static ArrayList<Node> nodes = new ArrayList<>();

    public Whiteboard(){
        setBackground(Color.decode("#D1D0F2"));

        addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // check for double click
                    int x = e.getX();
                    int y = e.getY();

                    drawCircle(x, y);
                }
            }
            
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                System.out.println(x + " " + y + "check: " + isInsideCircle(x, y));
                fromNode = isInsideCircle(x, y);
                if (fromNode != null) { // check if inside a circle
                    lastX = x;
                    lastY = y;
                }
            }
            public void mouseReleased(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                System.out.println(x + " " + y + "check: " + isInsideCircle(x, y));
                toNode = isInsideCircle(x, y);
                if (toNode != null && fromNode != toNode) { // check if inside a circle
                    drawLine(lastX, lastY, x, y);
                }
            }

            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
    }
    
    public void drawCircle(int x, int y) {
        Graphics g = getGraphics();
        g.setColor(Color.decode("#77A1D9"));
        g.fillOval(x - circleRadius, y - circleRadius, circleRadius * 2, circleRadius * 2);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(Character.toString(firstChar), x - 7, y + 7);

        // CREATE NEW NODE
        Node nodeA = new Node(firstChar);
        // nodes.add(nodeA);
        int [] point = {x, y};
        center.put(nodeA, point);

        firstChar++;    
    }   

    public void drawLine(int x1, int y1, int x2, int y2) {
        Graphics2D g = (Graphics2D) getGraphics();

        g.setStroke(new BasicStroke(5));
        Color transparentColor = new Color(255, 0, 0, 100);
        g.setColor(transparentColor);
        g.drawLine(x1, y1, x2, y2);

        String value = JOptionPane.showInputDialog("Enter the value:");
        int intVal = 0;
        boolean check = false;
        while(value == null || value.length() == 0 || !check){
            try{
                // value = JOptionPane.showInputDialog("Enter the value(integer):");
                intVal = Integer.parseInt(value);
                while(intVal < 1){
                    value = JOptionPane.showInputDialog("Invalid input!!! Enter the value(integer larger than 0):");
                    intVal = Integer.parseInt(value);
                }
                check = true;
            }
            catch(NumberFormatException e){
                value = JOptionPane.showInputDialog("Invalid input!!! Enter the value(integer):");
            }
            
        }
        System.out.println("Value is " + value + ",");

        // Calculate the midpoint of the line
        int midX = (x1 + x2) / 2;
        int midY = (y1 + y2) / 2;
        g.setColor(Color.BLACK); // set the color to black
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(value, midX, midY);

        fromNode.addEdge(new Edge(fromNode, toNode, intVal));
        toNode.addEdge(new Edge(toNode, fromNode, intVal));
    }

    public Node isInsideCircle(int x, int y) {
        for (Node i : center.keySet()){
            int dx = x - center.get(i)[0];
            int dy = y - center.get(i)[1];
            if(dx * dx + dy * dy <= circleRadius * circleRadius){
                return i;
            }
        }
        return null;
    }

    public HashMap<Node, int []> getNodes(){
        return Whiteboard.center;
    }
    
    public void reset() {
        Graphics g = getGraphics();
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        firstChar = 'A';
        lastX = -1;
        lastY = -1;
        center.clear();
        // nodes.clear();
    }

}
