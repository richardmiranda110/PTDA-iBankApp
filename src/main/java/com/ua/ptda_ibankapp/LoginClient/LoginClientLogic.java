/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package project.LoginClient;

import com.amazonaws.services.elasticmapreduce.model.Credentials;
import com.amazonaws.services.elasticmapreduce.model.UsernamePassword;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author ricar
 */
public class LoginClientLogic {

    private LoginClientTCPReciever reciever = null;
    private LoginClientTCPSender sender = null;

    public LoginClientLogic() {
        reciever = new LoginClientTCPReciever();
        sender = new LoginClientTCPSender();
    }
    //https://www.youtube.com/watch?v=LcNkQcs7jIg WATCH THIS, secalhar para dar load  a layout é top xuxa
    //https://youtu.be/-nDVy45eOYQ?list=PLL-4P1BOZnWwauNPsQ_Q13hVL1LHvhy30 para verificar exceptions em completablefuture
    //https://youtu.be/jVamXKqXn2Y?list=PLL-4P1BOZnWwauNPsQ_Q13hVL1LHvhy30 para adicionar timeout

    public void createAnswerAsyncThread() {

        CompletableFuture.supplyAsync(() -> reciever.recieveIncomingInteger())
                .thenApply(data -> jsonParser(data)
                //put the json parser here
                //what this will do is treat the value before giving it as final statement
                ).thenAccept(data -> {
                    //this is the info that the UI will recieve
                    System.out.println(String.format("Server response: %s\nActual response: %d", answer == 0 ? "correct" : "wrong password", data));
                }).thenRun(action -> openMainLayout());
    }

    public void showErrorMessage(String message) {
        JOptionPane optionPane = new JOptionPane(message);
        JDialog dialog = optionPane.createDialog("Error!");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);

        try {
            Thread.sleep(300);
            System.exit(0);
        } catch (InterruptedException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void sendLoginRequest(String username, char[] password) {
        try {
            sender.createSocketChannel();
            createAnswerAsyncThread();

            sender.createAndSendObject(generateCredentials(username, password));
            sender.closeSocketChannel();
        } catch (IOException ex) {
            Logger.getLogger(LoginClientLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Credentials generateCredentials(String username, char[] password) {
        return new Credentials().withUsernamePassword(generateUsernamePasswordObject(username, password));
    }

    private UsernamePassword generateUsernamePasswordObject(String username, char[] password) {
        UsernamePassword credentials = new UsernamePassword();
        credentials.setUsername(username);
        credentials.setPassword(new String(password));
        return credentials;
    }
}
