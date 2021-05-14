package client.core;

import client.model.chat.ChatModelClient;
import client.model.chat.ChatModelManagerClient;
import client.model.loan.LoanModelClient;
import client.model.loan.LoanModelManagerClient;
import client.model.material.MaterialModelClient;
import client.model.material.MaterialModelManagerClient;
import client.model.user.UserModelClient;
import client.model.user.UserModelManagerClient;
import client.network.Client;

public class ModelFactoryClient {
    private static ModelFactoryClient modelFactory;

    public static ModelFactoryClient getInstance() {
        if (modelFactory == null) {

            modelFactory = new ModelFactoryClient();
        }
        return modelFactory;
    }


    private ChatModelClient chatModelClient;

    public ChatModelClient getChatModelClient() {
        if (chatModelClient == null) {
            chatModelClient = new ChatModelManagerClient((Client) ClientFactory.getInstance()
                    .getClient());
        }
        return chatModelClient;
    }


    private LoanModelClient loanModelClient;

    public LoanModelClient getLoanModelClient() {
        if (loanModelClient == null) {
            loanModelClient = new LoanModelManagerClient((Client) ClientFactory.getInstance().getClient());
        }
        return loanModelClient;
    }


    private MaterialModelClient materialModelClient;

    public MaterialModelClient getMaterialModelClient() {
        if (materialModelClient == null) {
            materialModelClient = (MaterialModelClient) new MaterialModelManagerClient((Client) ClientFactory.getInstance().getClient());
        }
        return materialModelClient;
    }


    private UserModelClient userModelClient;

    public UserModelClient getUserModelClient() {
        if (userModelClient == null) {
            userModelClient = new UserModelManagerClient((Client) ClientFactory.getInstance().getClient());
        }
        return userModelClient;
    }
}
