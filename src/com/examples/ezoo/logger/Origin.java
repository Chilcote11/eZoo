package com.examples.ezoo.logger;

/**
 * This is the origin of any given logging message.
 * 
 * @author Cory Chilcote
 *
 */
public enum Origin {
	
	ANIMALDAO_GETALL,
	ANIMALDAO_SAVE,
	ANIMALDAO_DELETE,
	
	FSDAO_GETALL,
	FSDAO_GETBYANIMAL,
	FSDAO_SAVE,
	FSDAO_DELETE,
	FSDAO_ASSIGN,
	FSDAO_UNASSIGN,
	FSDAO_UPDATE,
	
	USERDAO_SAVE,
	USERDAO_DELETE,
	USERDAO_GETBYNAME,
	
	EVENTDAO_GETBYID, 
	EVENTDAO_GETALL, 
	EVENTDAO_GETEVENTSFORUSER, 
	EVENTDAO_SIGNUP, 
	EVENTDAO_LEAVE,
	EVENTDAO_GETNUMBERATTENDING,
	EVENTDAO_SAVE,
	EVENTDAO_DELETE,
	EVENTDAO_UPDATE,
	
	CONTROLLER_HOME_GET,
	CONTROLLER_ADDANIMAL_GET,
	CONTROLLER_ADDANIMAL_POST,
	CONTROLLER_ANIMALCARE_GET,
	CONTROLLER_FSCREATE_GET,
	CONTROLLER_FSCREATE_POST,
	CONTROLLER_DELETEANIMAL_POST,
	CONTROLLER_FSDELETE_POST,
	CONTROLLER_FS_GET,
	CONTROLLER_FSASSIGN_GET,
	CONTROLLER_FSASSIGN_POST,
	CONTROLLER_FSUNASSIGN_POST,
	CONTROLLER_FSUPDATE_GET,
	CONTROLLER_FSUPDATE_POST, 
	CONTROLLER_REGISTRATION_GET, 
	CONTROLLER_REGISTRATION_POST, 
	CONTROLLER_LOGIN_GET, 
	CONTROLLER_LOGIN_POST, 
	CONTROLLER_LOGINERROR_GET,
	CONTROLLER_PERSONALEVENTS_GET, 
	CONTROLLER_CALENDAR_GET, 
	CONTROLLER_EVENTCREATE_GET, 
	CONTROLLER_EVENTCREATE_POST, 
	CONTROLLER_EVENTDELETE_POST, 
	CONTROLLER_EVENTDETAILS_GET, 
	CONTROLLER_EVENTLEAVE_POST, 
	CONTROLLER_EVENTSIGNUP_POST, 
	CONTROLLER_EVENTUPDATE_GET,
	CONTROLLER_EVENTUPDATE_POST;

}
