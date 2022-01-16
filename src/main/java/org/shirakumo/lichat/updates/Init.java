// File has been auto-generated.
package org.shirakumo.lichat.updates;
import org.shirakumo.lichat.*;

public class Init{
    public static void init(){
        CL.makePackage("lichat");
        CL.makePackage("shirakumo");
        
        CL.registerClass(CL.intern("grant", "lichat"), Grant.class);
        CL.intern("update", "keyword");
        CL.intern("target", "lichat");
        
        CL.registerClass(CL.intern("search", "shirakumo"), Search.class);
        CL.intern("results", "keyword");
        CL.intern("query", "keyword");
        CL.intern("offset", "keyword");
        
        CL.registerClass(CL.intern("bridge", "shirakumo"), Bridge.class);
        
        CL.registerClass(CL.intern("no-such-user-info", "shirakumo"), NoSuchUserInfo.class);
        CL.intern("key", "keyword");
        
        CL.registerClass(CL.intern("blocked", "shirakumo"), Blocked.class);
        CL.intern("target", "keyword");
        
        CL.registerClass(CL.intern("last-read", "shirakumo"), LastRead.class);
        CL.intern("target", "keyword");
        CL.intern("update-id", "keyword");
        
        CL.registerClass(CL.intern("emote-list-full", "shirakumo"), EmoteListFull.class);
        
        CL.registerClass(CL.intern("no-such-user", "lichat"), NoSuchUser.class);
        
        CL.registerClass(CL.intern("set-channel-info", "shirakumo"), SetChannelInfo.class);
        CL.intern("key", "keyword");
        CL.intern("text", "lichat");
        
        CL.registerClass(CL.intern("typing", "shirakumo"), Typing.class);
        
        CL.registerClass(CL.intern("disconnect", "lichat"), Disconnect.class);
        
        CL.registerClass(CL.intern("username-taken", "lichat"), UsernameTaken.class);
        
        CL.registerClass(CL.intern("user-info", "lichat"), UserInfo.class);
        CL.intern("registered", "keyword");
        CL.intern("connections", "keyword");
        CL.intern("info", "shirakumo");
        
        CL.registerClass(CL.intern("too-many-connections", "lichat"), TooManyConnections.class);
        
        CL.registerClass(CL.intern("username-mismatch", "lichat"), UsernameMismatch.class);
        
        CL.registerClass(CL.intern("assume-identity", "shirakumo"), AssumeIdentity.class);
        CL.intern("key", "keyword");
        
        CL.registerClass(CL.intern("emotes", "shirakumo"), Emotes.class);
        CL.intern("names", "keyword");
        
        CL.registerClass(CL.intern("react", "shirakumo"), React.class);
        CL.intern("target", "keyword");
        CL.intern("update-id", "keyword");
        CL.intern("emote", "keyword");
        
        CL.registerClass(CL.intern("registration-rejected", "lichat"), RegistrationRejected.class);
        
        CL.registerClass(CL.intern("unquiet", "shirakumo"), Unquiet.class);
        CL.intern("target", "lichat");
        
        CL.registerClass(CL.intern("share-identity", "shirakumo"), ShareIdentity.class);
        CL.intern("key", "keyword");
        
        CL.registerClass(CL.intern("destroy", "shirakumo"), Destroy.class);
        
        CL.registerClass(CL.intern("channel-update", "lichat"), ChannelUpdate.class);
        CL.intern("channel", "keyword");
        CL.intern("bridge", "shirakumo");
        
        CL.registerClass(CL.intern("data", "shirakumo"), Data.class);
        CL.intern("content-type", "keyword");
        CL.intern("payload", "keyword");
        CL.intern("filename", "keyword");
        
        CL.registerClass(CL.intern("create", "lichat"), Create.class);
        CL.intern("channel", "keyword");
        
        CL.registerClass(CL.intern("channelname-taken", "lichat"), ChannelnameTaken.class);
        
        CL.registerClass(CL.intern("update", "lichat"), Update.class);
        CL.intern("clock", "keyword");
        CL.intern("from", "keyword");
        CL.intern("signature", "shirakumo");
        CL.intern("id", "keyword");
        
        CL.registerClass(CL.intern("ban", "shirakumo"), Ban.class);
        
        CL.registerClass(CL.intern("capabilities", "lichat"), Capabilities.class);
        CL.intern("permitted", "keyword");
        
        CL.registerClass(CL.intern("server-info", "lichat"), ServerInfo.class);
        CL.intern("attributes", "keyword");
        CL.intern("connections", "keyword");
        
        CL.registerClass(CL.intern("malformed-update", "lichat"), MalformedUpdate.class);
        
        CL.registerClass(CL.intern("leave", "lichat"), Leave.class);
        
        CL.registerClass(CL.intern("too-many-updates", "lichat"), TooManyUpdates.class);
        
        CL.registerClass(CL.intern("target-update", "lichat"), TargetUpdate.class);
        CL.intern("target", "keyword");
        
        CL.registerClass(CL.intern("set-user-info", "shirakumo"), SetUserInfo.class);
        CL.intern("key", "keyword");
        
        CL.registerClass(CL.intern("update-too-long", "lichat"), UpdateTooLong.class);
        
        CL.registerClass(CL.intern("too-many-channels", "lichat"), TooManyChannels.class);
        
        CL.registerClass(CL.intern("malformed-channel-info", "shirakumo"), MalformedChannelInfo.class);
        
        CL.registerClass(CL.intern("unban", "shirakumo"), Unban.class);
        
        CL.registerClass(CL.intern("users", "lichat"), Users.class);
        CL.intern("users", "keyword");
        
        CL.registerClass(CL.intern("connection-unstable", "lichat"), ConnectionUnstable.class);
        
        CL.registerClass(CL.intern("unshare-identity", "shirakumo"), UnshareIdentity.class);
        CL.intern("key", "keyword");
        
        CL.registerClass(CL.intern("message", "lichat"), Message.class);
        CL.intern("text", "lichat");
        CL.intern("reply-to", "shirakumo");
        CL.intern("link", "shirakumo");
        
        CL.registerClass(CL.intern("ip-unban", "shirakumo"), IpUnban.class);
        CL.intern("ip", "keyword");
        CL.intern("mask", "keyword");
        
        CL.registerClass(CL.intern("list-shared-identities", "shirakumo"), ListSharedIdentities.class);
        CL.intern("identities", "keyword");
        
        CL.registerClass(CL.intern("invalid-permissions", "lichat"), InvalidPermissions.class);
        
        CL.registerClass(CL.intern("no-such-channel-info", "shirakumo"), NoSuchChannelInfo.class);
        CL.intern("key", "keyword");
        
        CL.registerClass(CL.intern("update-failure", "lichat"), UpdateFailure.class);
        CL.intern("update-id", "keyword");
        
        CL.registerClass(CL.intern("incompatible-version", "lichat"), IncompatibleVersion.class);
        CL.intern("compatible-versions", "keyword");
        
        CL.registerClass(CL.intern("ip-blacklist", "shirakumo"), IpBlacklist.class);
        CL.intern("target", "keyword");
        
        CL.registerClass(CL.intern("bad-content-type", "shirakumo"), BadContentType.class);
        CL.intern("allowed-content-types", "keyword");
        
        CL.registerClass(CL.intern("text-update", "lichat"), TextUpdate.class);
        CL.intern("markup", "shirakumo");
        CL.intern("text", "keyword");
        CL.intern("rich", "shirakumo");
        
        CL.registerClass(CL.intern("malformed-user-info", "shirakumo"), MalformedUserInfo.class);
        
        CL.registerClass(CL.intern("quiet", "shirakumo"), Quiet.class);
        CL.intern("target", "lichat");
        
        CL.registerClass(CL.intern("unblock", "shirakumo"), Unblock.class);
        
        CL.registerClass(CL.intern("clock-skewed", "lichat"), ClockSkewed.class);
        
        CL.registerClass(CL.intern("emote", "shirakumo"), Emote.class);
        CL.intern("content-type", "keyword");
        CL.intern("payload", "keyword");
        CL.intern("name", "keyword");
        
        CL.registerClass(CL.intern("failure", "lichat"), Failure.class);
        
        CL.registerClass(CL.intern("channel-info", "shirakumo"), ChannelInfo.class);
        CL.intern("keys", "keyword");
        
        CL.registerClass(CL.intern("invalid-password", "lichat"), InvalidPassword.class);
        
        CL.registerClass(CL.intern("connect", "lichat"), Connect.class);
        CL.intern("version", "keyword");
        CL.intern("password", "keyword");
        CL.intern("extensions", "keyword");
        
        CL.registerClass(CL.intern("ip-ban", "shirakumo"), IpBan.class);
        CL.intern("ip", "keyword");
        CL.intern("mask", "keyword");
        
        CL.registerClass(CL.intern("edit", "shirakumo"), Edit.class);
        
        CL.registerClass(CL.intern("not-in-channel", "lichat"), NotInChannel.class);
        
        CL.registerClass(CL.intern("bad-ip-format", "shirakumo"), BadIpFormat.class);
        
        CL.registerClass(CL.intern("kick", "lichat"), Kick.class);
        CL.intern("target", "lichat");
        
        CL.registerClass(CL.intern("invalid-update", "lichat"), InvalidUpdate.class);
        
        CL.registerClass(CL.intern("backfill", "shirakumo"), Backfill.class);
        CL.intern("since", "keyword");
        
        CL.registerClass(CL.intern("deny", "lichat"), Deny.class);
        CL.intern("update", "keyword");
        CL.intern("target", "lichat");
        
        CL.registerClass(CL.intern("permissions", "lichat"), Permissions.class);
        CL.intern("permissions", "keyword");
        
        CL.registerClass(CL.intern("insufficient-permissions", "lichat"), InsufficientPermissions.class);
        
        CL.registerClass(CL.intern("join", "lichat"), Join.class);
        
        CL.registerClass(CL.intern("no-such-profile", "lichat"), NoSuchProfile.class);
        
        CL.registerClass(CL.intern("no-such-channel", "lichat"), NoSuchChannel.class);
        
        CL.registerClass(CL.intern("already-in-channel", "lichat"), AlreadyInChannel.class);
        
        CL.registerClass(CL.intern("pull", "lichat"), Pull.class);
        CL.intern("target", "lichat");
        
        CL.registerClass(CL.intern("blacklist", "shirakumo"), Blacklist.class);
        CL.intern("target", "keyword");
        
        CL.registerClass(CL.intern("pong", "lichat"), Pong.class);
        
        CL.registerClass(CL.intern("quieted", "shirakumo"), Quieted.class);
        CL.intern("target", "keyword");
        
        CL.registerClass(CL.intern("already-connected", "lichat"), AlreadyConnected.class);
        
        CL.registerClass(CL.intern("register", "lichat"), Register.class);
        CL.intern("password", "keyword");
        
        CL.registerClass(CL.intern("block", "shirakumo"), Block.class);
        
        CL.registerClass(CL.intern("bad-name", "lichat"), BadName.class);
        
        CL.registerClass(CL.intern("ping", "lichat"), Ping.class);
        
        CL.registerClass(CL.intern("no-such-parent-channel", "shirakumo"), NoSuchParentChannel.class);
        
        CL.registerClass(CL.intern("kill", "shirakumo"), Kill.class);
        
        CL.registerClass(CL.intern("channels", "lichat"), Channels.class);
        CL.intern("channels", "keyword");
        CL.intern("channel", "keyword");
        
        CL.registerClass(CL.intern("pause", "shirakumo"), Pause.class);
        CL.intern("by", "keyword");
    }
}