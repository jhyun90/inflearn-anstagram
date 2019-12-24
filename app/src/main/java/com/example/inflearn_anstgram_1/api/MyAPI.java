package com.example.inflearn_anstgram_1.api;

public class MyAPI {

    public static final String BASE_URL = "http://10.0.2.2:3000";
    public static final String GET_POST = BASE_URL + "/api/post/stories";

    /**
     {
         "id": 7,
         "username": "change-original-name",
         "image": {
             "url": "https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/3zR/image/ajpgDxkePtUXrN9nqZAfq_iKU70"
         },
         "text": "insane",
         "likes": {
             "count": 0,
             "liked": false
         },
         "created_at": "2019-12-16T05:50:50.680Z",
         "updated_at": "2019-12-16T07:11:07.703Z"
     }
     */

    public static class Post {
        int id;

        String username;
        Image image;
        String text;
        Likes likes;
        String created_at;
        String updated_at;

        public static class Image {
            String url;

            public String getUrl() {
                return url;
            }
        }

        public static class Likes {
            int count;
            boolean liked;

            public int getCount() {
                return count;
            }

            public boolean isLiked() {
                return liked;
            }
        }

        public int getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public Image getImage() {
            return image;
        }

        public String getText() {
            return text;
        }

        public Likes getLikes() {
            return likes;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }
    }
}
