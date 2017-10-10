package com.coresecure.brightcove.wrapper.api;

import com.coresecure.brightcove.wrapper.objects.*;
import com.coresecure.brightcove.wrapper.utils.JsonReader;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Cms {

    private static final Logger LOGGER = LoggerFactory.getLogger(Cms.class);

    private Account account;
    public final static int DEFAULT_LIMIT = 20;
    private final static int DEFAULT_OFFSET = 0;
    private final static String DEFAULT_ENCODING = "UTF-8";

    public Cms(Account aAccount) {
        LOGGER.debug("Cms Init aAccount " + aAccount.getAccount_ID());
        account = aAccount;
    }

    public JSONObject getPlayers() {
        JSONObject json = new JSONObject();
        account.login();
        Token authToken = account.getToken();
        if (authToken != null) {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", authToken.getTokenType() + " " + authToken.getToken());
            String urlParameters = "";
            String targetURL = "/accounts/" + account.getAccount_ID() + "/players";
            try {
                String response = account.platform.getPLAYERS_API(targetURL, urlParameters, headers);
                if (response != null && !response.isEmpty()) json = JsonReader.readJsonFromString(response);
            } catch (IOException e) {
                LOGGER.error("IOException", e);
                e.printStackTrace();
            } catch (JSONException e) {
                LOGGER.error("JSONException", e);
                e.printStackTrace();
            }

        }
        return json;
    }

    public JSONObject getVideosCount(String q) {
        JSONObject json = new JSONObject();
        account.login();
        Token authToken = account.getToken();
        if (authToken != null) {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", authToken.getTokenType() + " " + authToken.getToken());

            String targetURL = "/accounts/" + account.getAccount_ID() + "/counts/videos";
            try {
                String urlParameters = "q=" + ((q!= null) ? URLEncoder.encode(q, DEFAULT_ENCODING):"");
                String response = account.platform.getAPI(targetURL, urlParameters, headers);
                if (response != null && !response.isEmpty()) json = JsonReader.readJsonFromString(response);
            } catch (IOException e) {
                LOGGER.error("IOException", e);
                e.printStackTrace();
            } catch (JSONException e) {
                LOGGER.error("JSONException", e);
                e.printStackTrace();
            }

        }
        return json;
    }

    public JSONObject getPlaylistsCount() {
        JSONObject json = new JSONObject();
        account.login();
        Token authToken = account.getToken();
        if (authToken != null) {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", authToken.getTokenType() + " " + authToken.getToken());
            String urlParameters = "";
            String targetURL = "/accounts/" + account.getAccount_ID() + "/counts/playlists";
            try {
                String response = account.platform.getAPI(targetURL, urlParameters, headers);
                if (response != null && !response.isEmpty()) json = JsonReader.readJsonFromString(response);
            } catch (IOException e) {
                LOGGER.error("IOException", e);
                e.printStackTrace();
            } catch (JSONException e) {
                LOGGER.error("JSONException", e);
                e.printStackTrace();
            }

        }
        return json;
    }

    public JSONObject getVideo(String ID) {
        JSONObject json = new JSONObject();
        account.login();
        Token authToken = account.getToken();
        if (authToken != null) {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", authToken.getTokenType() + " " + authToken.getToken());
            String urlParameters = "";
            String targetURL = "/accounts/" + account.getAccount_ID() + "/videos/" + ID;
            try {
                String response = account.platform.getAPI(targetURL, urlParameters, headers);
                LOGGER.debug("getVideo response "+ response);
                if (response != null && !response.isEmpty()) json = JsonReader.readJsonFromString(response);
            } catch (IOException e) {
                LOGGER.error("IOException", e);
                e.printStackTrace();
            } catch (JSONException e) {
                LOGGER.error("JSONException", e);
                e.printStackTrace();
            }

        }
        return json;
    }

//    https://cms.api.brightcove.com/v1/accounts/{account_id}/video_fields



    public JSONObject getCustomFields() {
        JSONObject json = new JSONObject();
        account.login();
        Token authToken = account.getToken();
        if (authToken != null) {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", authToken.getTokenType() + " " + authToken.getToken());
            String urlParameters = "";
            String targetURL = "/accounts/" + account.getAccount_ID() + "/video_fields";
            try {
                String response = account.platform.getAPI(targetURL, urlParameters, headers);
                LOGGER.debug("getCustomFields response "+ response);
                if (response != null && !response.isEmpty()) json = JsonReader.readJsonFromString(response);
            } catch (IOException e) {
                LOGGER.error("IOException", e);
                e.printStackTrace();
            } catch (JSONException e) {
                LOGGER.error("JSONException", e);
                e.printStackTrace();
            }
        }
        return json;
    }


    public JSONObject createVideo(Video aVideo) {
        JSONObject json = new JSONObject();
        account.login();
        Token authToken = account.getToken();
        if (authToken != null) {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", authToken.getTokenType() + " " + authToken.getToken());
            String targetURL = "/accounts/" + account.getAccount_ID() + "/videos";
            try {

                JSONObject videoObj = aVideo.toJSON();
                videoObj.remove("account_id");
                String response = account.platform.postAPI(targetURL, aVideo.toJSON().toString(1), headers);
                if (response != null && !response.isEmpty()) json = JsonReader.readJsonFromString(response);
            } catch (IOException e) {
                LOGGER.error("IOException", e);
                e.printStackTrace();
            } catch (JSONException e) {
                LOGGER.error("JSONException", e);
                e.printStackTrace();
            }
        }
        LOGGER.trace("createVideo: "+aVideo.toString()+" Response: "+json.toString());
        return json;
    }
    public JSONObject createPlaylist(Playlist aPlaylist) {
        JSONObject json = new JSONObject();
        account.login();
        Token authToken = account.getToken();
        if (authToken != null) {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", authToken.getTokenType() + " " + authToken.getToken());
            String targetURL = "/accounts/" + account.getAccount_ID() + "/playlists";
            try {
                String response = account.platform.postAPI(targetURL, aPlaylist.toJSON().toString(1), headers);
                if (response != null && !response.isEmpty()) json = JsonReader.readJsonFromString(response);
            } catch (IOException e) {
                LOGGER.error("IOException", e);
                e.printStackTrace();
            } catch (JSONException e) {
                LOGGER.error("JSONException", e);
                e.printStackTrace();
            }
        }
        LOGGER.trace("createPlaylist: "+aPlaylist.toString()+" Response: "+json.toString());
        return json;
    }

    public JSONObject updateVideo(Video aVideo) {
        JSONObject json = new JSONObject();
        account.login();
        Token authToken = account.getToken();
        if (authToken != null) {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", authToken.getTokenType() + " " + authToken.getToken());
            String targetURL = "/accounts/" + account.getAccount_ID() + "/videos/"+aVideo.id;
            try {
                LOGGER.debug("targetURL: "+targetURL);
                JSONObject video = aVideo.toJSON();


                LOGGER.trace("UPDATE VIDEO DATA OBJECT: " + video.toString(1));
                video.remove("id");
                video.remove("account_id");

                String response = account.platform.patchAPI(targetURL, video.toString(1), headers);
                if (response != null && !response.isEmpty()) json = JsonReader.readJsonFromString(response);
            } catch (IOException e) {
                LOGGER.error("IOException", e);
                e.printStackTrace();
            } catch (JSONException e) {
                LOGGER.error("JSONException", e);
                e.printStackTrace();
            }
        }
        return json;
    }


    public JSONObject uploadInjest(String videoId, JSONObject payload)
    {
        JSONObject json = new JSONObject();
        account.login();
        Token authToken = account.getToken();
        if (authToken != null) {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", authToken.getTokenType() + " " + authToken.getToken());
            String targetURL = "/accounts/" + account.getAccount_ID() + "/videos/" + videoId + "/ingest-requests";

            try {
            LOGGER.trace("PAYLOAD PROTO>>: " + payload.toString(1));
            String response = account.platform.postDIRequest_API(targetURL, payload.toString(1), headers);

            if (response != null && !response.isEmpty()) json.put("response",response);

            }
            catch (JSONException e)
            {
                LOGGER.error("JSONException", e);
                e.printStackTrace();
            }
        }

        return json;
    }



    public JSONObject createIngest(Video aVideo, Ingest aIngest) {
        JSONObject json = new JSONObject();
        account.login();
        Token authToken = account.getToken();
        if (authToken != null) {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", authToken.getTokenType() + " " + authToken.getToken());
            String targetURL = "/accounts/" + account.getAccount_ID() + "/videos/" + aVideo.id + "/ingest-requests";
            try {
                String response = account.platform.postDI_API(targetURL, aIngest.toJSON().toString(1), headers);
                if (response != null && !response.isEmpty()) json = JsonReader.readJsonFromString(response);
            } catch (IOException e) {
                LOGGER.error("IOException", e);
                e.printStackTrace();
            } catch (JSONException e) {
                LOGGER.error("JSONException", e);
                e.printStackTrace();
            }
        }
        return json;
    }

    public JSONObject getIngestURL(String videoId, String filename) {
        JSONObject json = new JSONObject();
        account.login();
        Token authToken = account.getToken();
        if (authToken != null) {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", authToken.getTokenType() + " " + authToken.getToken());
            String targetURL = "/accounts/" + account.getAccount_ID() + "/videos/" + videoId + "/upload-urls/"+filename;
            LOGGER.trace("getIngestURL: "+targetURL);
            try {
                String response = account.platform.getDI_API(targetURL, "", headers);
                if (response != null && !response.isEmpty()) json = JsonReader.readJsonFromString(response);
            } catch (IOException e) {
                LOGGER.error("IOException", e);
                e.printStackTrace();
            } catch (JSONException e) {
                LOGGER.error("JSONException", e);
                e.printStackTrace();
            }
        }
        return json;
    }
    public JSONObject requestIngestURL(String videoId, String profile, String master, boolean getImages) {
        JSONObject json = new JSONObject();
        account.login();
        Token authToken = account.getToken();
        if (authToken != null) {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", authToken.getTokenType() + " " + authToken.getToken());
            String targetURL = "/accounts/" + account.getAccount_ID() + "/videos/" + videoId + "/ingest-requests";
            LOGGER.trace("requestIngestURL: "+targetURL);

            try {
                JSONObject payload = new JSONObject("{'master': {'url': '"+master+"'},'profile': '"+profile+"','capture-images': "+getImages+"}");

                String response = account.platform.postDIRequest_API(targetURL, payload.toString(1), headers);
                if (response != null && !response.isEmpty()) json = JsonReader.readJsonFromString(response);
            } catch (IOException e) {
                LOGGER.error("IOException", e);
                e.printStackTrace();
            } catch (JSONException e) {
                LOGGER.error("JSONException", e);
                e.printStackTrace();
            }
        }
        return json;
    }
    public JSONObject deleteVideo(String videoID) {
        JSONObject json = new JSONObject();
        account.login();
        Token authToken = account.getToken();
        if (authToken != null) {
            Map<String, String> headers = new HashMap<String, String>();
            System.out.println(authToken.getToken());
            headers.put("Authorization", authToken.getTokenType() + " " + authToken.getToken());
            String targetURL = "/accounts/" + account.getAccount_ID() + "/videos/" + videoID;
            try {
                String response = account.platform.deleteAPI(targetURL, videoID, headers);
                if (response != null && !response.isEmpty()) json = JsonReader.readJsonFromString(response);
                LOGGER.debug("deleteVideo response json:"+json.toString(1));
            } catch (IOException e) {
                try {
                    json.put("error_code", "IOException");
                    json.put("message", e.getMessage());
                }catch (JSONException ee) {
                    LOGGER.error("JSONException", ee);
                }
                LOGGER.error("IOException", e);
            } catch (JSONException e) {
                try {
                    json.put("error_code", "JSONException");
                    json.put("message", e.getMessage());
                }catch (JSONException ee) {
                    LOGGER.error("JSONException", ee);
                }
                LOGGER.error("JSONException", e);
            }
        }
        return json;
    }

    public JSONArray getVideoSources(String ID) {
        JSONArray json = new JSONArray();
        account.login();
        Token authToken = account.getToken();
        if (authToken != null) {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", authToken.getTokenType() + " " + authToken.getToken());
            String targetURL = "/accounts/" + account.getAccount_ID() + "/videos/" + ID + "/sources";
            try {
                String response = account.platform.getAPI(targetURL, "", headers);
                if (response != null && !response.isEmpty()) json = JsonReader.readJsonArrayFromString(response);
            } catch (IOException e) {
                LOGGER.error("IOException", e);
                e.printStackTrace();
            } catch (JSONException e) {
                LOGGER.error("JSONException", e);
                e.printStackTrace();
            }

        }
        return json;
    }

    public JSONObject getVideoImages(String ID) {
        JSONObject json = new JSONObject();
        account.login();
        Token authToken = account.getToken();
        if (authToken != null) {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", authToken.getTokenType() + " " + authToken.getToken());
            String targetURL = "/accounts/" + account.getAccount_ID() + "/videos/" + ID + "/images";
            try {
                String response = account.platform.getAPI(targetURL, "", headers);
                LOGGER.debug("getVideoImages response "+ response);

                if (response != null && !response.isEmpty()) json = JsonReader.readJsonFromString(response);
            } catch (IOException e) {
                LOGGER.error("IOException", e);
                e.printStackTrace();
            } catch (JSONException e) {
                LOGGER.error("JSONException", e);
                e.printStackTrace();
            }

        }
        return json;
    }

    public JSONArray getVideos(String q, int limit, int offset, String sort) {
        JSONArray json = new JSONArray();
        LOGGER.debug("account: " + account.getAccount_ID());
        account.login();
        Token authToken = account.getToken();
        LOGGER.debug("authToken: " + authToken.getToken());

        if (authToken != null) {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", authToken.getTokenType() + " " + authToken.getToken());

            try {
                q = (q != null) ? URLEncoder.encode(q, DEFAULT_ENCODING) : "";
                String urlParameters = "q=" + q + "%20%2Bstate:ACTIVE&limit=" + limit + "&offset=" + offset + (sort != null ? "&sort=" + sort:"");
                String targetURL = "/accounts/" + account.getAccount_ID() + "/videos";
                LOGGER.debug("urlParameters: " + urlParameters);
                String response = account.platform.getAPI(targetURL, urlParameters, headers);
                if (response != null && !response.isEmpty()) {
                    json = JsonReader.readJsonArrayFromString(response);
                    LOGGER.debug("response", response);
                    LOGGER.debug("json", json.toString());
                }
                if (json.length() == 0 && !q.isEmpty()) {
                    json.put(getVideo(q));
                }
            } catch (IOException e) {
                LOGGER.error("IOException", e);
                e.printStackTrace();
            } catch (JSONException e) {
                LOGGER.error("JSONException", e);
                e.printStackTrace();
            }


        }

        return json;
    }
    public JSONArray addThumbnail(JSONArray input) {
        JSONArray videos = new JSONArray();
        try {
            for (int i = 0; i < input.length(); i++) {

                JSONObject video = input.getJSONObject(i);
                if (video.has("id")) {
                    if (video.has("images") && video.getJSONObject("images").has("thumbnail")) {
                        video.put("thumbnailURL", video.getJSONObject("images").getJSONObject("thumbnail").getString("src"));
                    } else {
                        video.put("thumbnailURL", "/etc/designs/cs/brightcove/shared/img/noThumbnail.jpg");
                    }
                    videos.put(video);
                }
            }
        } catch (JSONException je) {
            LOGGER.error("JSONException", je);
        }
        return videos;
    }

    public JSONObject getVideoByRef(String ref_ID) {
        JSONObject json = new JSONObject();
        account.login();
        Token authToken = account.getToken();
        if (authToken != null) {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", authToken.getTokenType() + " " + authToken.getToken());
            String targetURL = "/accounts/" + account.getAccount_ID() + "/videos/ref:" + ref_ID;
            try {
                String response = account.platform.getAPI(targetURL, "", headers);
                if (response != null && !response.isEmpty()) json = JsonReader.readJsonFromString(response);
            } catch (IOException e) {
                LOGGER.error("IOException", e);
                e.printStackTrace();
            } catch (JSONException e) {
                LOGGER.error("JSONException", e);
                e.printStackTrace();
            }

        }
        return json;
    }

    public JSONArray getVideoSourcesByRef(String ref_ID) {
        JSONArray json = new JSONArray();
        account.login();
        Token authToken = account.getToken();
        if (authToken != null) {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", authToken.getTokenType() + " " + authToken.getToken());
            String targetURL = "/accounts/" + account.getAccount_ID() + "/videos/ref:" + ref_ID + "/sources";
            try {
                String response = account.platform.getAPI(targetURL, "", headers);
                if (response != null && !response.isEmpty()) json = JsonReader.readJsonArrayFromString(response);
            } catch (IOException e) {
                LOGGER.error("IOException", e);
                e.printStackTrace();
            } catch (JSONException e) {
                LOGGER.error("JSONException", e);
                e.printStackTrace();
            }

        }
        return json;
    }

    public JSONObject getPlaylist(String ref_ID) {
        JSONObject json = new JSONObject();
        account.login();
        Token authToken = account.getToken();
        if (authToken != null) {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", authToken.getTokenType() + " " + authToken.getToken());
            String targetURL = "/accounts/" + account.getAccount_ID() + "/playlists/" + ref_ID;
            try {
                String response = account.platform.getAPI(targetURL, "", headers);
                if (response != null && !response.isEmpty()) json = JsonReader.readJsonFromString(response);
            } catch (IOException e) {
                LOGGER.error("IOException", e);
                e.printStackTrace();
            } catch (JSONException e) {
                LOGGER.error("JSONException", e);
                e.printStackTrace();
            }

        }
        return json;
    }

    public JSONArray getPlaylists(int limit, int offset, String sort) {

        return getPlaylists( null,  limit,  offset,  sort);
    }
    public JSONArray getPlaylists(String q, int limit, int offset, String sort) {
        JSONArray json = new JSONArray();
        account.login();
        Token authToken = account.getToken();
        if (authToken != null) {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", authToken.getTokenType() + " " + authToken.getToken());
            String targetURL = "/accounts/" + account.getAccount_ID() + "/playlists";
            try {
                q = (q != null) ? URLEncoder.encode(q, DEFAULT_ENCODING) : "";
                String urlParameters = "q=" + q +"&limit=" + limit + "&offset=" + offset + "&sort=" + sort;
                String response = account.platform.getAPI(targetURL, urlParameters, headers);
                if (response != null && !response.isEmpty()) json = JsonReader.readJsonArrayFromString(response);
            } catch (IOException e) {
                LOGGER.error("IOException", e);
                e.printStackTrace();
            } catch (JSONException e) {
                LOGGER.error("JSONException", e);
                e.printStackTrace();
            }

        }
        return json;
    }
    public JSONObject getVideoImagesByRef(String ref_ID) {
        JSONObject json = new JSONObject();
        account.login();
        Token authToken = account.getToken();
        if (authToken != null) {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", authToken.getTokenType() + " " + authToken.getToken());
            String targetURL = "/accounts/" + account.getAccount_ID() + "/videos/ref:" + ref_ID + "/images";
            try {
                String response = account.platform.getAPI(targetURL, "", headers);
                if (response != null && !response.isEmpty()) json = JsonReader.readJsonFromString(response);
            } catch (IOException e) {
                LOGGER.error("IOException", e);
                e.printStackTrace();
            } catch (JSONException e) {
                LOGGER.error("JSONException", e);
                e.printStackTrace();
            }

        }
        return json;
    }

    // Command permutations
    public JSONArray getVideos() {
        return getVideos("", DEFAULT_LIMIT, DEFAULT_OFFSET, "");
    }

    public JSONArray getVideos(String q) {
        return getVideos(q, DEFAULT_LIMIT, DEFAULT_OFFSET, "");
    }

    public JSONArray getVideos(String q, String sort) {
        return getVideos(q, DEFAULT_LIMIT, DEFAULT_OFFSET, sort);
    }

    public JSONArray getVideos(String q, String sort, int limit) {
        return getVideos(q, limit, DEFAULT_OFFSET, sort);
    }

    public JSONArray getVideos(String q, int limit) {
        return getVideos(q, limit, DEFAULT_OFFSET, "");
    }

    public JSONArray getVideos(String q, int limit, int offset) {
        return getVideos(q, limit, offset, "");
    }

    public JSONArray getVideos(int limit, int offset, String sort) {
        return getVideos("", limit, offset, sort);
    }

}
