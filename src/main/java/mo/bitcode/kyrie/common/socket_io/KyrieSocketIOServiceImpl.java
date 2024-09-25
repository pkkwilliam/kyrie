package mo.bitcode.kyrie.common.socket_io;

import mo.bitcode.core.cache.impl.HashMapCacheService;
import mo.bitcode.core.security.jwt.JwtService;
import mo.bitcode.core.security.model.UserRole;
import mo.bitcode.core.web_socket.model.WebSocketClientConnectInfo;
import mo.bitcode.kyrie.repo.mongo.entity.UserProfile;
import mo.bitcode.socket_io.SocketIOServiceTemplate;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public class KyrieSocketIOServiceImpl extends SocketIOServiceTemplate<ObjectId, UserRole, UserProfile> {

  private static final boolean ALLOW_ANONYMOUS = true;

  public KyrieSocketIOServiceImpl(KyrieSocketIOConfig kyrieSocketIOConfig, JwtService<ObjectId, UserRole, UserProfile> jwtService) {
    super(kyrieSocketIOConfig.getSocketIOConfiguration(), jwtService, ALLOW_ANONYMOUS, new HashMapCacheService<>());
  }

  @Override
  public WebSocketClientConnectInfo getWebSocketClientConnectInfo() {
    return null;
  }

}
