package com.dnr.vimeo.model;

/**
 * Created by chaitanyaduse on 3/8/2016.
 */
public class VimeoTicket {
    private String uri;
    private String ticket_id;
    private String upload_link_secure;
    private String complete_uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getUpload_link_secure() {
        return upload_link_secure;
    }

    public void setUpload_link_secure(String upload_link_secure) {
        this.upload_link_secure = upload_link_secure;
    }

    public String getComplete_uri() {
        return complete_uri;
    }

    public void setComplete_uri(String complete_uri) {
        this.complete_uri = complete_uri;
    }
}
