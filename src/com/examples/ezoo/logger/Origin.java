package com.examples.ezoo.logger;

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
	CONTROLLER_LOGINERROR_GET;

}
