package br.com.trustsystems.elfinder.core.impl;

import br.com.trustsystems.elfinder.core.Target;
import br.com.trustsystems.elfinder.core.Volume;
import br.com.trustsystems.elfinder.core.VolumeBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
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
        return 0;
    }

    @Override
    public String getMimeType(Target target) throws IOException {
        return null;
    }

    @Override
    public String getAlias() {
        return null;
    }

    @Override
    public String getName(Target target) {
        return null;
    }

    @Override
    public Target getParent(Target target) {
        return null;
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
        return false;
    }

    @Override
    public boolean isFolder(Target target) {
        return false;
    }

    @Override
    public boolean isRoot(Target target) throws IOException {
        return getRootDir().equals(fromTarget(target));
    }

    @Override
    public Target[] listChildren(Target target) throws IOException {
//        List<String> childrenResultList = NioHelper.listChildrenNotHidden(fromTarget(target));
//        List<Target> targets = new ArrayList<>(childrenResultList.size());
//        for (Path path : childrenResultList) {
//            targets.add(fromPath(path));
//        }
//        return targets.toArray(new Target[targets.size()]);
        return  null;
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
