import javax.swing.*;
import java.awt.*;

public class myButton extends JButton{
    public myButton(String name){
        super(name);
        this.setFont(new Font("Arial", Font.BOLD, 15));
        this.setForeground(Color.decode("#314259"));
    }
}
