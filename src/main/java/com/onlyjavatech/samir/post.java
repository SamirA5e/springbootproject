//package com.onlyjavatech.samir;
//
//public class test {
//    @Entity(name = "Post")
//    @Table(name = "post")
//    public class Post {
//
//        @Id
//        @GeneratedValue
//        private Long id;
//
//        private String title;
//
//        public Post() {}
//
//        public Post(String title) {
//            this.title = title;
//        }
//
//        @ManyToMany(cascade = {
//                CascadeType.PERSIST,
//                CascadeType.MERGE
//        })
//        @JoinTable(name = "post_tag",
//                joinColumns = @JoinColumn(name = "post_id"),
//                inverseJoinColumns = @JoinColumn(name = "tag_id")
//        )
//        private List<Tag> tags = new ArrayList<>();
//
//        //Getters and setters ommitted for brevity
//
//        public void addTag(Tag tag) {
//            tags.add(tag);
//            tag.getPosts().add(this);
//        }
//
//        public void removeTag(Tag tag) {
//            tags.remove(tag);
//            tag.getPosts().remove(this);
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (!(o instanceof Post)) return false;
//            return id != null && id.equals(((Post) o).getId());
//        }
//
//        @Override
//        public int hashCode() {
//            return getClass().hashCode();
//        }
//    }
//}
