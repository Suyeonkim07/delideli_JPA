package flab.delideli.service.push;

import flab.delideli.dto.PushDTO;

public interface PushService {

	void sendToOwner(PushDTO pushDTO, String ownerId);

	void sendToUser(PushDTO pushDTO, String userId);

}
