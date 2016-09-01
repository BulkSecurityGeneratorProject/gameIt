package mk.gameIt.service;

import mk.gameIt.domain.Game;
import mk.gameIt.domain.User;
import mk.gameIt.domain.UserGameOrder;

public interface MailSender {

    void placeOrder(UserGameOrder userGameOrder);
    void sendReistrationSuccessEmail(User user);
    void sendNewsLetter(User user);
    void sendOrderEmail(User user, Game game);
}