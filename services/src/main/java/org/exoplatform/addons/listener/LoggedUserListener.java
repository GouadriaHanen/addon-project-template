package org.exoplatform.addons.listener;

import org.exoplatform.container.PortalContainer;
import org.exoplatform.container.component.ComponentRequestLifecycle;
import org.exoplatform.container.component.RequestLifeCycle;
import org.exoplatform.services.listener.Event;
import org.exoplatform.services.listener.Listener;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.services.organization.User;
import org.exoplatform.services.organization.UserHandler;
import org.exoplatform.services.security.ConversationRegistry;
import org.exoplatform.services.security.ConversationState;

import java.util.ArrayList;
import java.util.List;

public class LoggedUserListener extends Listener<ConversationRegistry, ConversationState> {
    private static final Log LOG = ExoLogger.getLogger(LoggedUserListener.class);

    public static final String  USER_PROFILE = "UserProfile";
    private PortalContainer container;
    private OrganizationService organizationService;

    private static List <User> ConnectedUser = new ArrayList<>();
    public LoggedUserListener(PortalContainer container) {
        this.container = container;
    }

    public LoggedUserListener() {

    }

    @Override
    public void onEvent(Event<ConversationRegistry, ConversationState> event) throws Exception
    {
        LOG.info("testing listener if it works first of all");
        if (organizationService == null) {
            organizationService = this.container.getComponentInstanceOfType(OrganizationService.class);
        }
        UserHandler userHandler = organizationService.getUserHandler();
        ConversationState state = event.getData();
        String userId = state.getIdentity().getUserId();
        boolean transactionOpened = false;
        if(organizationService instanceof ComponentRequestLifecycle) {
            RequestLifeCycle.begin((ComponentRequestLifecycle)organizationService);
            transactionOpened = true;
        }
        try {
            User user = (User) state.getAttribute(USER_PROFILE);

            if (user == null) {
                user = userHandler.findUserByName(userId);
                state.setAttribute(USER_PROFILE, user);
            }
            userHandler.saveUser(user, false);
            ConnectedUser.add(user);
            LOG.info("adding user "+user.getUserName());
            LOG.info("number of connected user :  "+ConnectedUser.size());
        } catch (Exception e) {
            LOG.error("Error while updating the last login time for user {}", userId, e);
        }finally {
            if(transactionOpened) {
                RequestLifeCycle.end();
            }
        }
    }

    public int ConnectedUserCount (){
        LOG.info("number of connected user :  "+ConnectedUser.size());
        return ConnectedUser.size();
    }
}
