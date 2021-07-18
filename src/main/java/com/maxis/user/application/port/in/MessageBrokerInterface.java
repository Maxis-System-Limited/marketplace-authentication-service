package com.maxis.user.application.port.in;

import com.maxis.user.domain.UserDomain;

public interface MessageBrokerInterface {
	UserDomain listenUserDomain(String message);
}
