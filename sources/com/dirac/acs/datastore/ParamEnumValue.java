package com.dirac.acs.datastore;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class ParamEnumValue extends GeneratedMessageV3 implements ParamEnumValueOrBuilder {
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int VALUE_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private double value_;
    private static final ParamEnumValue DEFAULT_INSTANCE = new ParamEnumValue();
    private static final Parser<ParamEnumValue> PARSER = new AbstractParser<ParamEnumValue>() { // from class: com.dirac.acs.datastore.ParamEnumValue.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ParamEnumValue m302parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new ParamEnumValue(input, extensionRegistry);
        }
    };

    private ParamEnumValue(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ParamEnumValue() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
    }

    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
        return new ParamEnumValue();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0010. Please report as an issue. */
    private ParamEnumValue(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            case 10:
                                String s = input.readStringRequireUtf8();
                                this.name_ = s;
                            case 17:
                                this.value_ = input.readDouble();
                            default:
                                if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                    done = true;
                                }
                        }
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                    }
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
        return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamEnumValue_descriptor;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamEnumValue_fieldAccessorTable.ensureFieldAccessorsInitialized(ParamEnumValue.class, Builder.class);
    }

    @Override // com.dirac.acs.datastore.ParamEnumValueOrBuilder
    public String getName() {
        Object ref = this.name_;
        if (ref instanceof String) {
            return (String) ref;
        }
        ByteString bs = (ByteString) ref;
        String s = bs.toStringUtf8();
        this.name_ = s;
        return s;
    }

    @Override // com.dirac.acs.datastore.ParamEnumValueOrBuilder
    public ByteString getNameBytes() {
        Object ref = this.name_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String) ref);
            this.name_ = b;
            return b;
        }
        return (ByteString) ref;
    }

    @Override // com.dirac.acs.datastore.ParamEnumValueOrBuilder
    public double getValue() {
        return this.value_;
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
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.name_);
        }
        double d = this.value_;
        if (d != 0.0d) {
            output.writeDouble(2, d);
        }
        this.unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
        int size = this.memoizedSize;
        if (size != -1) {
            return size;
        }
        int size2 = getNameBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.name_);
        double d = this.value_;
        if (d != 0.0d) {
            size2 += CodedOutputStream.computeDoubleSize(2, d);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = size3;
        return size3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ParamEnumValue)) {
            return super.equals(obj);
        }
        ParamEnumValue other = (ParamEnumValue) obj;
        return getName().equals(other.getName()) && Double.doubleToLongBits(getValue()) == Double.doubleToLongBits(other.getValue()) && this.unknownFields.equals(other.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = (((((((((((41 * 19) + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getValue()))) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hash;
        return hash;
    }

    public static ParamEnumValue parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (ParamEnumValue) PARSER.parseFrom(data);
    }

    public static ParamEnumValue parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ParamEnumValue) PARSER.parseFrom(data, extensionRegistry);
    }

    public static ParamEnumValue parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ParamEnumValue) PARSER.parseFrom(data);
    }

    public static ParamEnumValue parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ParamEnumValue) PARSER.parseFrom(data, extensionRegistry);
    }

    public static ParamEnumValue parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ParamEnumValue) PARSER.parseFrom(data);
    }

    public static ParamEnumValue parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ParamEnumValue) PARSER.parseFrom(data, extensionRegistry);
    }

    public static ParamEnumValue parseFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static ParamEnumValue parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static ParamEnumValue parseDelimitedFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static ParamEnumValue parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static ParamEnumValue parseFrom(CodedInputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static ParamEnumValue parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m299newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m301toBuilder();
    }

    public static Builder newBuilder(ParamEnumValue prototype) {
        return DEFAULT_INSTANCE.m301toBuilder().mergeFrom(prototype);
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m301toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m298newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ParamEnumValueOrBuilder {
        private Object name_;
        private double value_;

        public static final Descriptors.Descriptor getDescriptor() {
            return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamEnumValue_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamEnumValue_fieldAccessorTable.ensureFieldAccessorsInitialized(ParamEnumValue.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            this.name_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ParamEnumValue.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m312clear() {
            super.clear();
            this.name_ = "";
            this.value_ = 0.0d;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamEnumValue_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ParamEnumValue m325getDefaultInstanceForType() {
            return ParamEnumValue.getDefaultInstance();
        }

        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ParamEnumValue m306build() {
            ParamEnumValue result = m308buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ParamEnumValue m308buildPartial() {
            ParamEnumValue result = new ParamEnumValue(this);
            result.name_ = this.name_;
            result.value_ = this.value_;
            onBuilt();
            return result;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m323clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m336setField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.setField(field, value);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m314clearField(Descriptors.FieldDescriptor field) {
            return (Builder) super.clearField(field);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m317clearOneof(Descriptors.OneofDescriptor oneof) {
            return (Builder) super.clearOneof(oneof);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m338setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
            return (Builder) super.setRepeatedField(field, index, value);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m304addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.addRepeatedField(field, value);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m330mergeFrom(Message other) {
            if (other instanceof ParamEnumValue) {
                return mergeFrom((ParamEnumValue) other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(ParamEnumValue other) {
            if (other == ParamEnumValue.getDefaultInstance()) {
                return this;
            }
            if (!other.getName().isEmpty()) {
                this.name_ = other.name_;
                onChanged();
            }
            if (other.getValue() != 0.0d) {
                setValue(other.getValue());
            }
            m334mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m331mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            ParamEnumValue parsedMessage = null;
            try {
                try {
                    parsedMessage = (ParamEnumValue) ParamEnumValue.PARSER.parsePartialFrom(input, extensionRegistry);
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

        @Override // com.dirac.acs.datastore.ParamEnumValueOrBuilder
        public String getName() {
            Object ref = this.name_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString) ref;
                String s = bs.toStringUtf8();
                this.name_ = s;
                return s;
            }
            return (String) ref;
        }

        @Override // com.dirac.acs.datastore.ParamEnumValueOrBuilder
        public ByteString getNameBytes() {
            Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.name_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        public Builder setName(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.name_ = value;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = ParamEnumValue.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            if (value != null) {
                ParamEnumValue.checkByteStringIsUtf8(value);
                this.name_ = value;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.dirac.acs.datastore.ParamEnumValueOrBuilder
        public double getValue() {
            return this.value_;
        }

        public Builder setValue(double value) {
            this.value_ = value;
            onChanged();
            return this;
        }

        public Builder clearValue() {
            this.value_ = 0.0d;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m340setUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.setUnknownFields(unknownFields);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m334mergeUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.mergeUnknownFields(unknownFields);
        }
    }

    public static ParamEnumValue getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ParamEnumValue> parser() {
        return PARSER;
    }

    public Parser<ParamEnumValue> getParserForType() {
        return PARSER;
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ParamEnumValue m296getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
