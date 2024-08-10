package com.dirac.acs.datastore;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class Version extends GeneratedMessageV3 implements VersionOrBuilder {
    public static final int MAJOR_VERSION_FIELD_NUMBER = 1;
    public static final int MINOR_VERSION_FIELD_NUMBER = 2;
    public static final int PATCH_VERSION_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private int majorVersion_;
    private byte memoizedIsInitialized;
    private int minorVersion_;
    private int patchVersion_;
    private static final Version DEFAULT_INSTANCE = new Version();
    private static final Parser<Version> PARSER = new AbstractParser<Version>() { // from class: com.dirac.acs.datastore.Version.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Version m776parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Version(input, extensionRegistry);
        }
    };

    private Version(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Version() {
        this.memoizedIsInitialized = (byte) -1;
    }

    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
        return new Version();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0010. Please report as an issue. */
    private Version(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        boolean done = false;
        while (!done) {
            try {
                try {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                            case 8:
                                this.majorVersion_ = input.readInt32();
                            case 16:
                                this.minorVersion_ = input.readInt32();
                            case 24:
                                this.patchVersion_ = input.readInt32();
                            default:
                                if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                    done = true;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    }
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return DarInterface.internal_static_com_dirac_acs_datastore_Version_descriptor;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DarInterface.internal_static_com_dirac_acs_datastore_Version_fieldAccessorTable.ensureFieldAccessorsInitialized(Version.class, Builder.class);
    }

    @Override // com.dirac.acs.datastore.VersionOrBuilder
    public int getMajorVersion() {
        return this.majorVersion_;
    }

    @Override // com.dirac.acs.datastore.VersionOrBuilder
    public int getMinorVersion() {
        return this.minorVersion_;
    }

    @Override // com.dirac.acs.datastore.VersionOrBuilder
    public int getPatchVersion() {
        return this.patchVersion_;
    }

    public final boolean isInitialized() {
        byte isInitialized = this.memoizedIsInitialized;
        if (isInitialized == 1) {
            return true;
        }
        if (isInitialized == 0) {
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    public void writeTo(CodedOutputStream output) throws IOException {
        int i = this.majorVersion_;
        if (i != 0) {
            output.writeInt32(1, i);
        }
        int i2 = this.minorVersion_;
        if (i2 != 0) {
            output.writeInt32(2, i2);
        }
        int i3 = this.patchVersion_;
        if (i3 != 0) {
            output.writeInt32(3, i3);
        }
        this.unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
        int size = this.memoizedSize;
        if (size != -1) {
            return size;
        }
        int i = this.majorVersion_;
        int size2 = i != 0 ? 0 + CodedOutputStream.computeInt32Size(1, i) : 0;
        int i2 = this.minorVersion_;
        if (i2 != 0) {
            size2 += CodedOutputStream.computeInt32Size(2, i2);
        }
        int i3 = this.patchVersion_;
        if (i3 != 0) {
            size2 += CodedOutputStream.computeInt32Size(3, i3);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = size3;
        return size3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Version)) {
            return super.equals(obj);
        }
        Version other = (Version) obj;
        return getMajorVersion() == other.getMajorVersion() && getMinorVersion() == other.getMinorVersion() && getPatchVersion() == other.getPatchVersion() && this.unknownFields.equals(other.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = (((((((((((((((41 * 19) + getDescriptor().hashCode()) * 37) + 1) * 53) + getMajorVersion()) * 37) + 2) * 53) + getMinorVersion()) * 37) + 3) * 53) + getPatchVersion()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hash;
        return hash;
    }

    public static Version parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Version) PARSER.parseFrom(data);
    }

    public static Version parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Version) PARSER.parseFrom(data, extensionRegistry);
    }

    public static Version parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Version) PARSER.parseFrom(data);
    }

    public static Version parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Version) PARSER.parseFrom(data, extensionRegistry);
    }

    public static Version parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Version) PARSER.parseFrom(data);
    }

    public static Version parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Version) PARSER.parseFrom(data, extensionRegistry);
    }

    public static Version parseFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static Version parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static Version parseDelimitedFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static Version parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static Version parseFrom(CodedInputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static Version parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m773newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m775toBuilder();
    }

    public static Builder newBuilder(Version prototype) {
        return DEFAULT_INSTANCE.m775toBuilder().mergeFrom(prototype);
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m775toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m772newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements VersionOrBuilder {
        private int majorVersion_;
        private int minorVersion_;
        private int patchVersion_;

        public static final Descriptors.Descriptor getDescriptor() {
            return DarInterface.internal_static_com_dirac_acs_datastore_Version_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DarInterface.internal_static_com_dirac_acs_datastore_Version_fieldAccessorTable.ensureFieldAccessorsInitialized(Version.class, Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Version.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m786clear() {
            super.clear();
            this.majorVersion_ = 0;
            this.minorVersion_ = 0;
            this.patchVersion_ = 0;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return DarInterface.internal_static_com_dirac_acs_datastore_Version_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Version m799getDefaultInstanceForType() {
            return Version.getDefaultInstance();
        }

        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Version m780build() {
            Version result = m782buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Version m782buildPartial() {
            Version result = new Version(this);
            result.majorVersion_ = this.majorVersion_;
            result.minorVersion_ = this.minorVersion_;
            result.patchVersion_ = this.patchVersion_;
            onBuilt();
            return result;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m797clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m810setField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.setField(field, value);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m788clearField(Descriptors.FieldDescriptor field) {
            return (Builder) super.clearField(field);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m791clearOneof(Descriptors.OneofDescriptor oneof) {
            return (Builder) super.clearOneof(oneof);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m812setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
            return (Builder) super.setRepeatedField(field, index, value);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m778addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.addRepeatedField(field, value);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m804mergeFrom(Message other) {
            if (other instanceof Version) {
                return mergeFrom((Version) other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Version other) {
            if (other == Version.getDefaultInstance()) {
                return this;
            }
            if (other.getMajorVersion() != 0) {
                setMajorVersion(other.getMajorVersion());
            }
            if (other.getMinorVersion() != 0) {
                setMinorVersion(other.getMinorVersion());
            }
            if (other.getPatchVersion() != 0) {
                setPatchVersion(other.getPatchVersion());
            }
            m808mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m805mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Version parsedMessage = null;
            try {
                try {
                    parsedMessage = (Version) Version.PARSER.parsePartialFrom(input, extensionRegistry);
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                }
            } finally {
                if (parsedMessage != null) {
                    mergeFrom(parsedMessage);
                }
            }
        }

        @Override // com.dirac.acs.datastore.VersionOrBuilder
        public int getMajorVersion() {
            return this.majorVersion_;
        }

        public Builder setMajorVersion(int value) {
            this.majorVersion_ = value;
            onChanged();
            return this;
        }

        public Builder clearMajorVersion() {
            this.majorVersion_ = 0;
            onChanged();
            return this;
        }

        @Override // com.dirac.acs.datastore.VersionOrBuilder
        public int getMinorVersion() {
            return this.minorVersion_;
        }

        public Builder setMinorVersion(int value) {
            this.minorVersion_ = value;
            onChanged();
            return this;
        }

        public Builder clearMinorVersion() {
            this.minorVersion_ = 0;
            onChanged();
            return this;
        }

        @Override // com.dirac.acs.datastore.VersionOrBuilder
        public int getPatchVersion() {
            return this.patchVersion_;
        }

        public Builder setPatchVersion(int value) {
            this.patchVersion_ = value;
            onChanged();
            return this;
        }

        public Builder clearPatchVersion() {
            this.patchVersion_ = 0;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m814setUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.setUnknownFields(unknownFields);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m808mergeUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.mergeUnknownFields(unknownFields);
        }
    }

    public static Version getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Version> parser() {
        return PARSER;
    }

    public Parser<Version> getParserForType() {
        return PARSER;
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Version m770getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
