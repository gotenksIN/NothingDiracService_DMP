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
public final class ParamVoid extends GeneratedMessageV3 implements ParamVoidOrBuilder {
    private static final ParamVoid DEFAULT_INSTANCE = new ParamVoid();
    private static final Parser<ParamVoid> PARSER = new AbstractParser<ParamVoid>() { // from class: com.dirac.acs.datastore.ParamVoid.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ParamVoid m563parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new ParamVoid(input, extensionRegistry);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;

    private ParamVoid(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ParamVoid() {
        this.memoizedIsInitialized = (byte) -1;
    }

    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
        return new ParamVoid();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0010. Please report as an issue. */
    private ParamVoid(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        boolean done = false;
        while (!done) {
            try {
                try {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                        default:
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                    }
                } catch (IOException e) {
                    throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamVoid_descriptor;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamVoid_fieldAccessorTable.ensureFieldAccessorsInitialized(ParamVoid.class, Builder.class);
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
        this.unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
        int size = this.memoizedSize;
        if (size != -1) {
            return size;
        }
        int size2 = 0 + this.unknownFields.getSerializedSize();
        this.memoizedSize = size2;
        return size2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ParamVoid)) {
            return super.equals(obj);
        }
        ParamVoid other = (ParamVoid) obj;
        return this.unknownFields.equals(other.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = (((41 * 19) + getDescriptor().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hash;
        return hash;
    }

    public static ParamVoid parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (ParamVoid) PARSER.parseFrom(data);
    }

    public static ParamVoid parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ParamVoid) PARSER.parseFrom(data, extensionRegistry);
    }

    public static ParamVoid parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ParamVoid) PARSER.parseFrom(data);
    }

    public static ParamVoid parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ParamVoid) PARSER.parseFrom(data, extensionRegistry);
    }

    public static ParamVoid parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ParamVoid) PARSER.parseFrom(data);
    }

    public static ParamVoid parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ParamVoid) PARSER.parseFrom(data, extensionRegistry);
    }

    public static ParamVoid parseFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static ParamVoid parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static ParamVoid parseDelimitedFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static ParamVoid parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static ParamVoid parseFrom(CodedInputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static ParamVoid parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m560newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m562toBuilder();
    }

    public static Builder newBuilder(ParamVoid prototype) {
        return DEFAULT_INSTANCE.m562toBuilder().mergeFrom(prototype);
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m562toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m559newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ParamVoidOrBuilder {
        public static final Descriptors.Descriptor getDescriptor() {
            return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamVoid_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamVoid_fieldAccessorTable.ensureFieldAccessorsInitialized(ParamVoid.class, Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ParamVoid.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m573clear() {
            super.clear();
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamVoid_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ParamVoid m586getDefaultInstanceForType() {
            return ParamVoid.getDefaultInstance();
        }

        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ParamVoid m567build() {
            ParamVoid result = m569buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ParamVoid m569buildPartial() {
            ParamVoid result = new ParamVoid(this);
            onBuilt();
            return result;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m584clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m597setField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.setField(field, value);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m575clearField(Descriptors.FieldDescriptor field) {
            return (Builder) super.clearField(field);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m578clearOneof(Descriptors.OneofDescriptor oneof) {
            return (Builder) super.clearOneof(oneof);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m599setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
            return (Builder) super.setRepeatedField(field, index, value);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m565addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.addRepeatedField(field, value);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m591mergeFrom(Message other) {
            if (other instanceof ParamVoid) {
                return mergeFrom((ParamVoid) other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(ParamVoid other) {
            if (other == ParamVoid.getDefaultInstance()) {
                return this;
            }
            m595mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m592mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            ParamVoid parsedMessage = null;
            try {
                try {
                    parsedMessage = (ParamVoid) ParamVoid.PARSER.parsePartialFrom(input, extensionRegistry);
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

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m601setUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.setUnknownFields(unknownFields);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m595mergeUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.mergeUnknownFields(unknownFields);
        }
    }

    public static ParamVoid getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ParamVoid> parser() {
        return PARSER;
    }

    public Parser<ParamVoid> getParserForType() {
        return PARSER;
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ParamVoid m557getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
