package com.wisdom.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class FileTypeUtils
{
    public static final String JPG_AND_PNG = "jpg,png";
    public static final String JPEG = "jpeg";
    public static final String JPG = "jpg";
    public static final String PNG = "png";
    public static final String GIF = "gif";
    public static final String PDF = "pdf";
    public static final String DOC = "doc";
    public static final String DOCX = "docx";
    public static final String MP4 = "mp4";
    public static final Map<String, String> MAGIC_NUMBER_MAP = Maps.newHashMap();

    public static String getFileType(File file)
    {
        Preconditions.checkArgument(file != null, "Param file must be not null");
        Preconditions.checkArgument(!file.isDirectory(), "Param file must be not Directory");
        Preconditions.checkArgument(file.exists(), "Param url must be exists");
        try {
            ByteSource byteSource = Files.asByteSource(file);
            byte[] bytes = byteSource.read();
            return getFileType(bytes); } catch (IOException e) {
            throw new RuntimeException("getFileType error.", e);
        }

    }

    public static String getFileType(byte[] bytes)
    {
        Preconditions.checkArgument((bytes != null) && (bytes.length > 3), "Param bytes must be not null and empty");
        try
        {
            byte[] fileTypeByteArr = Arrays.copyOfRange(bytes, 0, 4);

            String fileTypeMagic = HexByteTransformer.bytes2Hex(fileTypeByteArr);

            String fileType = (String)MAGIC_NUMBER_MAP.get(fileTypeMagic.toUpperCase());
            if (fileType == null) {
                throw new RuntimeException("代表文件类型的Magic Number为:" + fileTypeMagic + ",但服务端未配置对应文件类型");
            }
            return fileType; } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }


    }

    static
    {
        MAGIC_NUMBER_MAP.put("FFD8FFE0", "jpg");
        MAGIC_NUMBER_MAP.put("FFD8FFDB", "jpg");
        MAGIC_NUMBER_MAP.put("FFD8FFE1", "jpg");
        MAGIC_NUMBER_MAP.put("2D2D2D2D", "jpg,png");
        MAGIC_NUMBER_MAP.put("89504E47", "png");
        MAGIC_NUMBER_MAP.put("47494638", "gif");
        MAGIC_NUMBER_MAP.put("25504446", "pdf");
        MAGIC_NUMBER_MAP.put("D0CF11E0", "doc");
        MAGIC_NUMBER_MAP.put("504B0304", "docx");
        MAGIC_NUMBER_MAP.put("00000018", "mp4");
        MAGIC_NUMBER_MAP.put("0000001C", "mp4");
    }
}