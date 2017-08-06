package ControllerTests;

import Controller.LoginController;
import Controller.MessageController;
import Controller.UserController;
import Service.LoginService;
import Session.SessionUtil;
import de.akquinet.jbosscc.needle.annotation.ObjectUnderTest;
import de.akquinet.jbosscc.needle.junit.NeedleRule;
import de.akquinet.jbosscc.needle.mock.EasyMockProvider;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import model.User;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;

public class LoginControllerTests {

    private final String DEFAULT_USERNAME = "TestUSER";
    private final String DEFAULT_PASSWORD = "TestPassword";

    private User user;

    @Rule
    public NeedleRule needleRule = new NeedleRule();

    @ObjectUnderTest
    private LoginController loginController;

    @Inject
    private EasyMockProvider mockProvider;

    @Inject
    private HttpSession httpSessionMock;

    @Inject
    private UserController userController;

    @Inject
    private LoginService loginService;

    @Inject
    private MessageController messageController;

    @Inject
    private SessionUtil sessionUtil;

    @Before
    public void beforeTest() {
        initUser();
    }

    @After
    public void afterTest() {
        mockProvider.verifyAll();
    }

    @Test
    public void testLoginFailed() throws Exception {

        loginController.setUsername(DEFAULT_USERNAME);
        loginController.setPassword(DEFAULT_PASSWORD);

        EasyMock.expect(
                loginService.login(DEFAULT_USERNAME, DEFAULT_PASSWORD))
                .andReturn(null);

        messageController.sendFacesMessage(
                "Invalid Login!",
                "Please Try Again!",
                FacesMessage.SEVERITY_WARN);

        EasyMock.expectLastCall().once();

        mockProvider.replayAll();

        loginController.login();
        Assert.assertEquals("", loginController.getPassword());
        Assert.assertEquals("", loginController.getUsername());
    }

    @Test
    public void testLoginSuccess() throws Exception {
        loginController.setUsername(DEFAULT_USERNAME);
        loginController.setPassword(DEFAULT_PASSWORD);

        EasyMock.expect(
                loginService.login(DEFAULT_USERNAME, DEFAULT_PASSWORD))
                .andReturn(user);

        EasyMock.expect(sessionUtil.getSession()).andReturn(httpSessionMock);

        httpSessionMock.setAttribute("username", DEFAULT_USERNAME);
        EasyMock.expectLastCall().once();

        userController.setUser(user);
        EasyMock.expectLastCall().once();

        messageController.sendFacesMessage(
                "Willkommen " + user.getUserName(),
                "Dies ist die Entwicklungsumgebung!",
                FacesMessage.SEVERITY_INFO);

        EasyMock.expectLastCall().once();

        mockProvider.replayAll();

        loginController.login();
        Assert.assertEquals("", loginController.getPassword());
        Assert.assertEquals("", loginController.getUsername());
    }

    public void initUser() {
        this.user = new User();
        this.user.setUserName(DEFAULT_USERNAME);
        this.user.setUserPassword(DEFAULT_PASSWORD);
    }
}
