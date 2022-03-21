//package com.onlyjavatech.samir;
//
//public class Tag {
//    @Entity(name = "Tag")
//    @Table(name = "tag")
//    public class Tag {
//
//        @Id
//        @GeneratedValue
//        private Long id;
//
//        @NaturalId
//        private String name;
//
//        @ManyToMany(mappedBy = "tags")
//        private List<Post> posts = new ArrayList<>();
//
//        public Tag() {}
//
//        public Tag(String name) {
//            this.name = name;
//        }
//
//        //Getters and setters ommitted for brevity
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Tag tag = (Tag) o;
//            return Objects.equals(name, tag.name);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(name);
//        }
//    }
//}
