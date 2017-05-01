package pomoce;


import javax.swing.*;

/**r
 * Created by User on 2016-12-28.
 */
public class Powiadomienie {    //JEST OK

        public static void komunikat (String infoMessage, String titleBar)
        {
            JOptionPane.showMessageDialog(null, infoMessage,titleBar, JOptionPane.INFORMATION_MESSAGE);
        }
}
