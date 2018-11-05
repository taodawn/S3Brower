package br.com.trustsystems.elfinder.core.impl;

import br.com.trustsystems.elfinder.DBUtils;
import br.com.trustsystems.elfinder.core.Target;
import br.com.trustsystems.elfinder.core.Volume;
import br.com.trustsystems.elfinder.core.VolumeBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class S3FileSystemVolume implements Volume {

    private final String alias;
    private final String rootDir;

    public String getRootDir() {
        return rootDir;
    }

    public S3FileSystemVolume(Builder builder) {
        this.alias = builder.alias;
        this.rootDir = builder.rootDir;
    }

    @Override
    public void createFile(Target target) throws IOException {

    }

    @Override
    public void createFolder(Target target) throws IOException {

    }

    @Override
    public void deleteFile(Target target) throws IOException {

    }

    @Override
    public void deleteFolder(Target target) throws IOException {

    }

    @Override
    public boolean exists(Target target) {
        return false;
    }

    @Override
    public Target fromPath(String path) {
        return fromPath(this, path);
    }

    public static Target fromPath(S3FileSystemVolume volume, String path) {
        return new S3FileSystemTarget(volume, path);
    }

    @Override
    public long getLastModified(Target target) throws IOException {
        return (new Date()).getTime();
    }

    @Override
    public String getMimeType(Target target) throws IOException {
        String path = ((S3FileSystemTarget) target).getPath();
        if(path.endsWith("jpg")){
            return "image/jpeg";
        }else if(path.endsWith("txt")){
            return "text/plain";
        }
        return "directory";
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public String getName(Target target) {
        String path = ((S3FileSystemTarget) target).getPath();
        String p = "";
        if (path.endsWith("/")) {
            String tmp = path.substring(0, path.length() - 1);
            p = (tmp.substring(tmp.lastIndexOf("/") + 1));
        } else {
            p = (path.substring(path.lastIndexOf("/") + 1));
        }
        return p;

    }

    @Override
    public Target getParent(Target target) {
        String path = ((S3FileSystemTarget) target).getPath();
        String p = "";
        if (path.endsWith("/")) {
            String tmp = path.substring(0, path.length() - 1);
            p = (tmp.substring(0, tmp.lastIndexOf("/") + 1));
        } else {
            p = (path.substring(0, path.lastIndexOf("/") + 1));
        }
        return fromPath(p);
    }

    public static String fromTarget(Target target) {
        return ((S3FileSystemTarget) target).getPath();
    }

    @Override
    public String getPath(Target target) throws IOException {
        return fromTarget(target);
    }

    @Override
    public Target getRoot() {
        return fromPath(getRootDir());
    }

    @Override
    public long getSize(Target target) throws IOException {
        return 0;
    }

    @Override
    public boolean hasChildFolder(Target target) throws IOException {
        if (!((S3FileSystemTarget) target).getPath().endsWith("/")) {
            return false;
        }
        List<String> childrenResultList = DBUtils.search("select path from s3 where bucket='" + getAlias() + "' and path like '" + ((S3FileSystemTarget) target).getPath() + "%' ");

        List<Target> targets = new ArrayList<>(childrenResultList.size());
        for (String path : childrenResultList) {
            if (path.indexOf("/", ((S3FileSystemTarget) target).getPath().length() + 1) > 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isFolder(Target target) {
        return ((S3FileSystemTarget) target).getPath().endsWith("/");
    }

    @Override
    public boolean isRoot(Target target) throws IOException {
        return getRootDir().equals(fromTarget(target));
    }

    @Override
    public Target[] listChildren(Target target) throws IOException {
        List<String> childrenResultList = DBUtils.search("select path from s3 where bucket='" + getAlias() + "' and path like '" + ((S3FileSystemTarget) target).getPath() + "%' order by path");

        List<Target> targets = new ArrayList<>(childrenResultList.size());
        String p = ((S3FileSystemTarget) target).getPath();
        String latestPath="";
        for (String path : childrenResultList) {
            if(path.equals(p)){
                continue;
            }
            int i = path.indexOf("/", p.length());
            if (i < 0) {
                if (latestPath.equals(path)) {
                    continue;
                }
                targets.add(fromPath(path));
                latestPath = path;
            } else {
                if (latestPath.equals((path.substring(0, i + 1)))) {
                    continue;
                }
                targets.add(fromPath(path.substring(0, i + 1)));
                latestPath = path.substring(0, i + 1);
            }
        }
        return targets.toArray(new Target[targets.size()]);

    }

    @Override
    public InputStream openInputStream(Target target) throws IOException {
        return null;
    }

    @Override
    public OutputStream openOutputStream(Target target) throws IOException {
        return null;
    }

    @Override
    public void rename(Target origin, Target destination) throws IOException {

    }

    @Override
    public List<Target> search(String target) throws IOException {
        return null;
    }

    public static S3FileSystemVolume.Builder builder(String alias, String rootDir) {
        return new S3FileSystemVolume.Builder(alias, rootDir);
    }

    public static class Builder implements VolumeBuilder<S3FileSystemVolume> {
        // required fields
        private final String alias;
        private final String rootDir;

        public Builder(String alias, String rootDir) {
            this.alias = alias;
            this.rootDir = rootDir;
        }

        @Override
        public S3FileSystemVolume build() {
            return new S3FileSystemVolume(this);
        }
    }
}
