import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.shephertz.app42.paas.sdk.java.App42Response;
import com.shephertz.app42.paas.sdk.java.ServiceAPI;
import com.shephertz.app42.paas.sdk.java.buddy.Buddy;
import com.shephertz.app42.paas.sdk.java.buddy.BuddyService;
import com.shephertz.app42.paas.sdk.java.geo.GeoPoint;
import com.shephertz.app42.paas.sdk.java.user.UserService;


public class BuddyTestCase {
	ServiceAPI sp = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		sp = new ServiceAPI(TestAccessCredentials.apiKey,
				TestAccessCredentials.secretKey);
		sp.setBaseURL(TestAccessCredentials.protocol,
				TestAccessCredentials.host, TestAccessCredentials.port);
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testSendFriendRequest() {
		BuddyService buddyService = sp.buildBuddyService();
		String userName = "userName" + new Date().getTime();
		String buddyName = "buddy" + new Date().getTime();
		String message = "Hi i am using app42 buddy request";
		Buddy buddy = buddyService.sendFriendRequest(userName, buddyName,
				message);
		System.out.println("Response Of SendFriendRequest:: " + buddy);
		assertEquals(buddy.getBuddyName(), buddyName);
		assertEquals(buddy.getUserName(), userName);
	}
	@Test
	public void testGetFriendRequest() {
		BuddyService buddyService = sp.buildBuddyService();
		UserService userService = sp.buildUserService();
		String userName = "userName" + new Date().getTime();
		String buddyName = "buddyName" + new Date().getTime();
		String message = "Hi i am using app42 buddy request";
		String email = "Nick" + new Date().getTime() + "@gmail.com";
		userService.createUser(userName,"password","nick1@example.com");
		userService.createUser(buddyName, "password", email);
		buddyService.sendFriendRequest(userName, buddyName, message);

		ArrayList<Buddy> buddy = buddyService.getFriendRequest(buddyName);

		System.out.println("Response Of GetFriendRequest:" + buddy);

	}
	
	@Test
	public void testAcceptFriendRequest() {
		BuddyService buddyService = sp.buildBuddyService();
		String userName = "userName" + new Date().getTime();
		String buddyName = "buddyName" + new Date().getTime();
		String message = "hi i m using App42 Api";
		buddyService.sendFriendRequest(userName, buddyName, message);
		buddyService.getFriendRequest(buddyName);
		Buddy buddy = buddyService.acceptFriendRequest(buddyName, userName);
		System.out.println("Response Of AcceptFriendRequest ::" + buddy);
		
	}
	@Test
	public void testRejectFriendRequest() {
		BuddyService buddyService = sp.buildBuddyService();
		String userName = "userName" + new Date().getTime();
		String buddyName = "buddyName" + new Date().getTime();
		String message = "hi i m using App42 Api";
		buddyService.sendFriendRequest(userName, buddyName, message);
		Buddy buddy = buddyService.rejectFriendRequest(buddyName, userName);
		System.out.println("Response Of  RejectFriendRequest :: " + buddy);
		
	}
	
	@Test
	public void testGetAllFriends() {
		BuddyService buddyService = sp.buildBuddyService();
		String userName = "userName" + new Date().getTime();
		String buddyName = "buddyName" + new Date().getTime();
		String buddyName2 = "secondBuddyName" + new Date().getTime();
		String message = "hi i m using App42 Api" + new Date().getTime();
		buddyService.sendFriendRequest(userName, buddyName, message);
		buddyService.sendFriendRequest(userName, buddyName2, message);
		buddyService.acceptFriendRequest(buddyName, userName);
		buddyService.acceptFriendRequest(buddyName2, userName);
		ArrayList<Buddy> buddy = buddyService.getAllFriends(buddyName);

		System.out.println("Response Of GetAllFriends::" + buddy);

	}
	@Test
	public void testCreateGroupByUser() throws JSONException {
		BuddyService buddyService = sp.buildBuddyService();
		String userName = "userName" + new Date().getTime();
		String groupName = "groupName" + new Date().getTime();
		Buddy buddy = buddyService.createGroupByUser(userName, groupName);
		System.out.println("response Of CreateGroupByUser ::" + buddy);
		
	}
	@Test
	public void testAddFriendToGroup() throws JSONException {
		BuddyService buddyService = sp.buildBuddyService();
		String userName = "userName" + new Date().getTime();
		String buddyName = "buddyName" + new Date().getTime();
		String groupName = "groupName" + new Date().getTime();
		String message = "hi i m using App42 Api" + new Date().getTime();
		buddyService.sendFriendRequest(userName, buddyName, message);
		buddyService.sendFriendRequest(userName, "secondBuddy", message);
		buddyService.getFriendRequest(buddyName);
		buddyService.acceptFriendRequest(buddyName, userName);
		buddyService.acceptFriendRequest("secondBuddy", userName);
		buddyService.createGroupByUser(userName, groupName);
		ArrayList<String> userList = new ArrayList<String>();
		userList.add(buddyName);
		userList.add("secondBuddy");
		ArrayList<Buddy> buddy = buddyService.addFriendToGroup(userName,
				groupName, userList);
		System.out.println("Response Of AddFriendToGroup :: " + buddy);
	
	}
	@Test
	public void testAddFriendToGroupWithGeoTag() throws JSONException {
		BuddyService buddyService = sp.buildBuddyService();
		String userName = "userName" + new Date().getTime();
		String buddyName = "buddyName" + new Date().getTime();
		String groupName = "groupName" + new Date().getTime();
		String message = "hi i m using App42 Api" + new Date().getTime();
		buddyService.sendFriendRequest(userName, buddyName, message);
		buddyService.sendFriendRequest(userName, "secondBuddy", message);
		buddyService.getFriendRequest(buddyName);
		buddyService.acceptFriendRequest(buddyName, userName);
		buddyService.acceptFriendRequest("secondBuddy", userName);
		GeoPoint gp1 = new GeoPoint();
		gp1.setLng(new BigDecimal(77.319999999999940000));
		gp1.setLat(new BigDecimal(28.570000000000000000));
		gp1.setMarker("Himalya");
		buddyService.checkedInGeoLocation(buddyName, gp1);
		buddyService.createGroupByUser(userName, groupName);
		ArrayList<String> userList = new ArrayList<String>();
		userList.add(buddyName);
		userList.add("secondBuddy");
		ArrayList<Buddy> buddy1 = buddyService.addFriendToGroup(userName,
				groupName, userList);
		System.out.println("Response Of AddFriendToGroupWithGeoTag ::" + buddy1);
	
	}
	@Test
	public void testGetFriendsByLocation() throws JSONException {
		BuddyService buddyService = sp.buildBuddyService();
		String userName = "userName" + new Date().getTime();
		String buddyName = "buddyName" + new Date().getTime();
		String secondBuddy = "secondBuddy" + new Date().getTime();
		String message = "hi i m using App42 Api" + new Date().getTime();
		buddyService.sendFriendRequest(userName, buddyName, message);
		buddyService.sendFriendRequest(userName, secondBuddy, message);
		buddyService.acceptFriendRequest(buddyName, userName);
		buddyService.acceptFriendRequest(secondBuddy, userName);
		GeoPoint gp = new GeoPoint();
		gp.setLng(new BigDecimal(77.083787000000030000));
		gp.setLat(new BigDecimal(28.465736400000000000));
		gp.setMarker("gurgaon");
		GeoPoint gp1 = new GeoPoint();
		gp1.setLng(new BigDecimal(77.319999999999940000));
		gp1.setLat(new BigDecimal(28.570000000000000000));
		gp1.setMarker("noida");
		Buddy buddy = buddyService.checkedInGeoLocation(secondBuddy, gp);
		buddyService.checkedInGeoLocation(secondBuddy, gp1);
		System.out.println("response is " + buddy);
		ArrayList<Buddy> buddy1 = buddyService.getFriendsByLocation(userName,
				new BigDecimal(28.635308000000000000), new BigDecimal(
						77.224960000000010000), new BigDecimal(100), 2);
		System.out.println("Response Of GetFriendByLocation::"+buddy1);
	}
	@Test
	public void testGetAllGroups() throws JSONException {
		BuddyService buddyService = sp.buildBuddyService();
		String userName = "userName" + new Date().getTime();
		String groupName = "groupName" + new Date().getTime();
		buddyService.createGroupByUser(userName, groupName + "1");
		buddyService.createGroupByUser(userName, groupName);
		buddyService.createGroupByUser(userName, groupName + "2");
		buddyService.createGroupByUser(userName, groupName + "3");
		ArrayList<Buddy> getAllGroups = buddyService.getAllGroups(userName);
		System.out.println("Response Of GetAllGroups :: " + getAllGroups);
		

	}
	
	@Test
	public void testGetAllFriendsInGroup() throws JSONException {
		BuddyService buddyService = sp.buildBuddyService();
		String userName = "userName" + new Date().getTime();
		String buddyName = "buddyName" + new Date().getTime();
		String groupName = "groupName" + new Date().getTime();
		String message = "hi i am shashank" + new Date().getTime();
		buddyService.sendFriendRequest(userName, buddyName, message);
		buddyService.sendFriendRequest(userName, "secondBuddy", message);
		buddyService.sendFriendRequest("secondBuddy", buddyName, message);
		buddyService.acceptFriendRequest(buddyName, userName);
		buddyService.acceptFriendRequest("secondBuddy", userName);
		buddyService.acceptFriendRequest(buddyName, "secondBuddy");
		buddyService.createGroupByUser(userName, groupName);
		ArrayList<String> userList = new ArrayList<String>();
		userList.add(buddyName);
		userList.add("secondBuddy");
		buddyService.addFriendToGroup(userName, groupName, userList);
		ArrayList<Buddy> buddy1 = buddyService.getAllFriendsInGroup(buddyName,
				userName, groupName);
		System.out.println("Response Of GetAllFriendsInGroup " + buddy1);
		
	}
	@Test
	public void testBlockFriendRequest() {
		BuddyService buddyService = sp.buildBuddyService();
		String userName = "userName" + new Date().getTime();
		String buddyName = "buddyName" + new Date().getTime();
		String message = "Hi i am using app42 buddy request";
		buddyService.sendFriendRequest(userName, buddyName, message);
		Buddy buddy1 = buddyService.blockFriendRequest(buddyName, userName);
		System.out.println("Response Of BlockFriendRequest ::"+buddy1);
		assertEquals(buddy1.getBuddyName(), userName);
		assertEquals(buddy1.getUserName(), buddyName);
	}
	@Test
	public void testBlockUser() {
		BuddyService buddyService = sp.buildBuddyService();
		String userName = "userName" + new Date().getTime();
		String buddyName = "buddyName" + new Date().getTime();
		Buddy buddy = buddyService.blockUser(userName, buddyName);
		System.out.println("Response Of BlockUser :: " + buddy);
		assertEquals(buddy.getUserName(), userName);
		assertEquals(buddy.buddyName, buddyName);
	}
	@Test
	public void testUnBlockUser() {
		BuddyService buddyService = sp.buildBuddyService();
		String userName = "userName" + new Date().getTime();
		String buddyName = "buddyName" + new Date().getTime();
		buddyService.blockUser(userName, buddyName);
		Buddy unbuddy = buddyService.unblockUser(userName, buddyName);
		System.out.println("Response Of UnblockUser ::" + unbuddy);
		assertEquals(unbuddy.getUserName(), userName);
		assertEquals(unbuddy.buddyName, buddyName);
	}
	@Test
	public void testSendMessageToGroup() {
		BuddyService buddyService = sp.buildBuddyService();
		String userName = "userName" + new Date().getTime();
		String buddyName = "buddyName" + new Date().getTime();
		String groupName = "groupName" + new Date().getTime();
		String message = "message" + new Date().getTime();
		buddyService.sendFriendRequest(userName, buddyName, message);
		buddyService.sendFriendRequest(userName, "secondBuddy", message);
		buddyService.acceptFriendRequest(buddyName, userName);
		buddyService.acceptFriendRequest("secondBuddy", userName);
		buddyService.createGroupByUser(userName, groupName);
		ArrayList<String> userList = new ArrayList<String>();
		userList.add(buddyName);
		userList.add("secondBuddy");
		buddyService.addFriendToGroup(userName, groupName, userList);
		ArrayList<Buddy> sendBuddy = buddyService.sendMessageToGroup(
				"secondBuddy", userName, groupName, "Hi");
		System.out.println("Response Of SendMessageToGroup ::"+sendBuddy);

		assertEquals(sendBuddy.get(0).getBuddyName(), userName);
		assertEquals(sendBuddy.get(0).getGroupName(), groupName);
		assertEquals(sendBuddy.get(0).getUserName(), "secondBuddy");
		assertEquals(sendBuddy.get(0).getOwnerName(), userName);
	}
	@Test
	public void testSendMessageToFriend() {
		BuddyService buddyService = sp.buildBuddyService();
		String userName = "userName" + new Date().getTime();
		String buddyName = "buddyName" + new Date().getTime();
		String message = "message";
		buddyService.sendFriendRequest(userName, buddyName, message);
		buddyService.sendFriendRequest(userName, "secondBuddy", message);
		buddyService.getFriendRequest(buddyName);
		buddyService.acceptFriendRequest(buddyName, userName);
		buddyService.acceptFriendRequest("secondBuddy", userName);
		Buddy sendBuddy = buddyService.sendMessageToFriend(userName,
				"secondBuddy", "Hi");
		System.out.println("Response Of SendMessageToFriend ::"+sendBuddy);
		
		assertEquals(sendBuddy.getBuddyName(), "secondBuddy");
		assertEquals(sendBuddy.getUserName(), userName);
	}
	@Test
	public void testSendMessageToFriends() {
		BuddyService buddyService = sp.buildBuddyService();
		String userName = "userName" + new Date().getTime();
		String buddyName = "buddyName" + new Date().getTime();
		String newBuddy = "newBuddy" + new Date().getTime();
		String message = "Message" + new Date().getTime();
		buddyService.sendFriendRequest(userName, buddyName, message);
		buddyService.sendFriendRequest(userName, newBuddy, message);
		buddyService.getFriendRequest(buddyName);
		buddyService.acceptFriendRequest(buddyName, userName);
		buddyService.acceptFriendRequest(newBuddy, userName);
		ArrayList<Buddy> sendBuddy = buddyService.sendMessageToFriends(
				userName,message);
		System.out.println("Response Of SendMessageToFriends ::"+sendBuddy);
		
		assertEquals(sendBuddy.get(0).getBuddyName(), buddyName);
		assertEquals(sendBuddy.get(0).getUserName(), userName);
	}
	@Test
	
		public void testGetAllMessages() {
			BuddyService buddyService = sp.buildBuddyService();
			String userName = "userName" + new Date().getTime();
			String buddyName = "buddyName" + new Date().getTime();
			String newBuddy = "newBuddy" + new Date().getTime();
			String message = "message";
			buddyService.sendFriendRequest(newBuddy, buddyName, message);
			buddyService.sendFriendRequest(userName, newBuddy, message);
			buddyService.acceptFriendRequest(buddyName, newBuddy);
			buddyService.acceptFriendRequest(newBuddy, userName);
			buddyService.sendMessageToFriends(newBuddy,message);
			ArrayList<Buddy> getAll = buddyService.getAllMessages(newBuddy);
			System.out.println("Response Of GetAllMessages ::"+getAll);
			assertEquals(getAll.get(0).getUserName(), newBuddy);
			assertEquals(getAll.get(0).getOwnerName(), newBuddy);
}
	@Test
	public void testGetAllMessagesFromBuddy() {
		BuddyService buddyService = sp.buildBuddyService();
		String userName = "userName" + new Date().getTime();
		String buddyName = "buddyName" + new Date().getTime();
		String newBuddy = "newBuddy" + new Date().getTime();
		String message = "message" + new Date().getTime();
		buddyService.sendFriendRequest(userName, buddyName, message);
		buddyService.sendFriendRequest(newBuddy, buddyName, message);
		buddyService.acceptFriendRequest(buddyName, userName);
		buddyService.acceptFriendRequest(buddyName, newBuddy);
		buddyService.sendMessageToFriend(userName, buddyName, message);
		ArrayList<Buddy> sendBuddy1 = buddyService.getAllMessagesFromBuddy(
				buddyName, userName);
		System.out.println("Response Of GetAllMessagesFromBuddy ::"+sendBuddy1);
		assertEquals(sendBuddy1.get(0).getUserName(), buddyName);
		assertEquals(sendBuddy1.get(0).getOwnerName(), userName);
	}
	@Test
	public void testGetAllMessagesFromGroup() {
		BuddyService buddyService = sp.buildBuddyService();
		String userName = "userName" + new Date().getTime();
		String buddyName = "buddyName" + new Date().getTime();
		String groupName = "groupName" + new Date().getTime();
		String message = "Message" + new Date().getTime();
		buddyService.sendFriendRequest(userName, buddyName, message);
		buddyService.acceptFriendRequest(buddyName, userName);
		buddyService.createGroupByUser(userName, groupName);
		ArrayList<String> userList1 = new ArrayList<String>();
		userList1.add(buddyName);
		buddyService.addFriendToGroup(userName, groupName, userList1);
		buddyService.sendMessageToGroup(userName, userName, groupName, message);
		ArrayList<Buddy> sendBuddy1 = buddyService.getAllMessagesFromGroup(
				buddyName, userName, groupName);
		System.out.println("Response Of GetAllMessagesFromGroup ::"+sendBuddy1);
		assertEquals(sendBuddy1.get(0).getUserName(), buddyName);
		assertEquals(sendBuddy1.get(0).getOwnerName(), userName);
	}
	@Test
	public void testDeleteMessageById() {
		BuddyService buddyService = sp.buildBuddyService();
		String userName = "userName" + new Date().getTime();
		String buddyName = "buddyName" + new Date().getTime();
		String message = "message"+ new Date().getTime();
		buddyService.sendFriendRequest(userName, buddyName, message);
		buddyService.acceptFriendRequest(buddyName, userName);
		Buddy sendBuddy = buddyService.sendMessageToFriend(userName, buddyName,
				message);
		App42Response delete = buddyService.deleteMessageById(userName,
				sendBuddy.getMessageId());
		System.out.println("Response Of DeleteMessageById ::"+delete);
	}
	@Test
	public void testDeleteMessageByIds() {
		BuddyService buddyService = sp.buildBuddyService();
		String userName = "userName" + new Date().getTime();
		String buddyName = "buddyName" + new Date().getTime();
		String message = "message"+ new Date().getTime();
		buddyService.sendFriendRequest(userName, buddyName, message);
		buddyService.acceptFriendRequest(buddyName, userName);
		Buddy sendBuddy = buddyService.sendMessageToFriend(userName, buddyName,
				message);
		Buddy sendBuddy1 = buddyService.sendMessageToFriend(userName,
				buddyName,message);
		ArrayList<String> idList = new ArrayList<String>();
		System.out.println(sendBuddy.getMessageId());
		System.out.println(sendBuddy1.getMessageId());
		idList.add(sendBuddy.getMessageId());
		idList.add(sendBuddy1.getMessageId());
		App42Response delete = buddyService
				.deleteMessageByIds(userName, idList);
		System.out.println("Response Of DeleteMessageByIds ::"+delete);
	}
	@Test
	public void testUnFriend() {
		BuddyService buddyService = sp.buildBuddyService();
		String userName = "userName" + new Date().getTime();
		String buddyName = "buddyName" + new Date().getTime();
		String message = "hi i m using App42 Api"+ new Date().getTime();
		buddyService.sendFriendRequest(userName, buddyName, message);
		buddyService.acceptFriendRequest(buddyName, userName);
		App42Response unfriend = buddyService.unFriend(buddyName, userName);
		System.out.println("Response Of Unfriend " + unfriend);
	}

}
