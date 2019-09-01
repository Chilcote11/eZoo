//package com.examples.ezoo.config;
//
//import java.io.Serializable;
//
//import org.springframework.security.access.PermissionEvaluator;
//import org.springframework.security.core.Authentication;
//
//import com.examples.ezoo.model.User;
//
//public class CustomPermissionEvaluator implements PermissionEvaluator {
//
//	@Override
//	public boolean hasPermission(Authentication auth, Object target, Object permission) {
//		
//		// for debugging:
//		System.out.println("auth: " + auth);
//		System.out.println("target: " + target);
//		System.out.println("permission: " + permission);
//		
//		// TODO add custom logic.. not exactly sure how this will work yet
//		if (target instanceof User) {
//			User user = (User) target;
//			
//			if (auth.getAuthorities().equals("ROLE_ADMIN")) {	// this probably isn't right
//				return true;
//			}
//		}
//		
//		return false;
//	}
//
//	@Override
//	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
//			Object permission) {
//		return false;
//	}
//
//}
