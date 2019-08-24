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
	
	CONTROLLER_ADDANIMAL,
	CONTROLLER_ANIMALCARE,
	CONTROLLER_FSCREATE,
	CONTROLLER_DELETEANIMAL,
	CONTROLLER_FS,
	CONTROLLER_FSASSIGN,
	CONTROLLER_FSUNASSIGN,
	CONTROLLER_FSUPDATE;	

}
