package com.dnr.vimeo.model;

public  class VimeoVideoMetaRequest {
        private String name, description;
        private Privacy privacy;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Privacy getPrivacy() {
            return privacy;
        }

        public void setPrivacy(Privacy privacy) {
            this.privacy = privacy;
        }
    }