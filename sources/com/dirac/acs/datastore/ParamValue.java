package com.dirac.acs.datastore;

import com.dirac.acs.datastore.Id;
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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class ParamValue extends GeneratedMessageV3 implements ParamValueOrBuilder {
    public static final int ID_FIELD_NUMBER = 1;
    public static final int UPDATED_FIELD_NUMBER = 3;
    public static final int VALUE_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private Id id_;
    private byte memoizedIsInitialized;
    private boolean updated_;
    private double value_;
    private static final ParamValue DEFAULT_INSTANCE = new ParamValue();
    private static final Parser<ParamValue> PARSER = new AbstractParser<ParamValue>() { // from class: com.dirac.acs.datastore.ParamValue.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ParamValue m467parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new ParamValue(input, extensionRegistry);
        }
    };

    private ParamValue(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ParamValue() {
        this.memoizedIsInitialized = (byte) -1;
    }

    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
        return new ParamValue();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0010. Please report as an issue. */
    private ParamValue(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                        case 10:
                            Id id = this.id_;
                            Id.Builder subBuilder = id != null ? id.m202toBuilder() : null;
                            Id id2 = (Id) input.readMessage(Id.parser(), extensionRegistry);
                            this.id_ = id2;
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(id2);
                                this.id_ = subBuilder.m209buildPartial();
                            }
                        case 17:
                            this.value_ = input.readDouble();
                        case 24:
                            this.updated_ = input.readBool();
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
        return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamValue_descriptor;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamValue_fieldAccessorTable.ensureFieldAccessorsInitialized(ParamValue.class, Builder.class);
    }

    @Override // com.dirac.acs.datastore.ParamValueOrBuilder
    public boolean hasId() {
        return this.id_ != null;
    }

    @Override // com.dirac.acs.datastore.ParamValueOrBuilder
    public Id getId() {
        Id id = this.id_;
        return id == null ? Id.getDefaultInstance() : id;
    }

    @Override // com.dirac.acs.datastore.ParamValueOrBuilder
    public IdOrBuilder getIdOrBuilder() {
        return getId();
    }

    @Override // com.dirac.acs.datastore.ParamValueOrBuilder
    public double getValue() {
        return this.value_;
    }

    @Override // com.dirac.acs.datastore.ParamValueOrBuilder
    public boolean getUpdated() {
        return this.updated_;
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
        if (this.id_ != null) {
            output.writeMessage(1, getId());
        }
        double d = this.value_;
        if (d != 0.0d) {
            output.writeDouble(2, d);
        }
        boolean z = this.updated_;
        if (z) {
            output.writeBool(3, z);
        }
        this.unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
        int size = this.memoizedSize;
        if (size != -1) {
            return size;
        }
        int size2 = this.id_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getId()) : 0;
        double d = this.value_;
        if (d != 0.0d) {
            size2 += CodedOutputStream.computeDoubleSize(2, d);
        }
        boolean z = this.updated_;
        if (z) {
            size2 += CodedOutputStream.computeBoolSize(3, z);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = size3;
        return size3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ParamValue)) {
            return super.equals(obj);
        }
        ParamValue other = (ParamValue) obj;
        if (hasId() != other.hasId()) {
            return false;
        }
        return (!hasId() || getId().equals(other.getId())) && Double.doubleToLongBits(getValue()) == Double.doubleToLongBits(other.getValue()) && getUpdated() == other.getUpdated() && this.unknownFields.equals(other.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = (41 * 19) + getDescriptor().hashCode();
        if (hasId()) {
            hash = (((hash * 37) + 1) * 53) + getId().hashCode();
        }
        int hash2 = (((((((((hash * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getValue()))) * 37) + 3) * 53) + Internal.hashBoolean(getUpdated())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hash2;
        return hash2;
    }

    public static ParamValue parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (ParamValue) PARSER.parseFrom(data);
    }

    public static ParamValue parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ParamValue) PARSER.parseFrom(data, extensionRegistry);
    }

    public static ParamValue parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ParamValue) PARSER.parseFrom(data);
    }

    public static ParamValue parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ParamValue) PARSER.parseFrom(data, extensionRegistry);
    }

    public static ParamValue parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ParamValue) PARSER.parseFrom(data);
    }

    public static ParamValue parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ParamValue) PARSER.parseFrom(data, extensionRegistry);
    }

    public static ParamValue parseFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static ParamValue parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static ParamValue parseDelimitedFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static ParamValue parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static ParamValue parseFrom(CodedInputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static ParamValue parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m464newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m466toBuilder();
    }

    public static Builder newBuilder(ParamValue prototype) {
        return DEFAULT_INSTANCE.m466toBuilder().mergeFrom(prototype);
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m466toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m463newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ParamValueOrBuilder {
        private SingleFieldBuilderV3<Id, Id.Builder, IdOrBuilder> idBuilder_;
        private Id id_;
        private boolean updated_;
        private double value_;

        public static final Descriptors.Descriptor getDescriptor() {
            return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamValue_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamValue_fieldAccessorTable.ensureFieldAccessorsInitialized(ParamValue.class, Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ParamValue.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m477clear() {
            super.clear();
            if (this.idBuilder_ == null) {
                this.id_ = null;
            } else {
                this.id_ = null;
                this.idBuilder_ = null;
            }
            this.value_ = 0.0d;
            this.updated_ = false;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamValue_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ParamValue m490getDefaultInstanceForType() {
            return ParamValue.getDefaultInstance();
        }

        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ParamValue m471build() {
            ParamValue result = m473buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ParamValue m473buildPartial() {
            ParamValue result = new ParamValue(this);
            SingleFieldBuilderV3<Id, Id.Builder, IdOrBuilder> singleFieldBuilderV3 = this.idBuilder_;
            if (singleFieldBuilderV3 == null) {
                result.id_ = this.id_;
            } else {
                result.id_ = singleFieldBuilderV3.build();
            }
            result.value_ = this.value_;
            result.updated_ = this.updated_;
            onBuilt();
            return result;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m488clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m501setField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.setField(field, value);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m479clearField(Descriptors.FieldDescriptor field) {
            return (Builder) super.clearField(field);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m482clearOneof(Descriptors.OneofDescriptor oneof) {
            return (Builder) super.clearOneof(oneof);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m503setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
            return (Builder) super.setRepeatedField(field, index, value);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m469addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.addRepeatedField(field, value);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m495mergeFrom(Message other) {
            if (other instanceof ParamValue) {
                return mergeFrom((ParamValue) other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(ParamValue other) {
            if (other == ParamValue.getDefaultInstance()) {
                return this;
            }
            if (other.hasId()) {
                mergeId(other.getId());
            }
            if (other.getValue() != 0.0d) {
                setValue(other.getValue());
            }
            if (other.getUpdated()) {
                setUpdated(other.getUpdated());
            }
            m499mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m496mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            ParamValue parsedMessage = null;
            try {
                try {
                    parsedMessage = (ParamValue) ParamValue.PARSER.parsePartialFrom(input, extensionRegistry);
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

        @Override // com.dirac.acs.datastore.ParamValueOrBuilder
        public boolean hasId() {
            return (this.idBuilder_ == null && this.id_ == null) ? false : true;
        }

        @Override // com.dirac.acs.datastore.ParamValueOrBuilder
        public Id getId() {
            SingleFieldBuilderV3<Id, Id.Builder, IdOrBuilder> singleFieldBuilderV3 = this.idBuilder_;
            if (singleFieldBuilderV3 == null) {
                Id id = this.id_;
                return id == null ? Id.getDefaultInstance() : id;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setId(Id value) {
            SingleFieldBuilderV3<Id, Id.Builder, IdOrBuilder> singleFieldBuilderV3 = this.idBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.id_ = value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(value);
            }
            return this;
        }

        public Builder setId(Id.Builder builderForValue) {
            SingleFieldBuilderV3<Id, Id.Builder, IdOrBuilder> singleFieldBuilderV3 = this.idBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.id_ = builderForValue.m207build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builderForValue.m207build());
            }
            return this;
        }

        public Builder mergeId(Id value) {
            SingleFieldBuilderV3<Id, Id.Builder, IdOrBuilder> singleFieldBuilderV3 = this.idBuilder_;
            if (singleFieldBuilderV3 == null) {
                Id id = this.id_;
                if (id != null) {
                    this.id_ = Id.newBuilder(id).mergeFrom(value).m209buildPartial();
                } else {
                    this.id_ = value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(value);
            }
            return this;
        }

        public Builder clearId() {
            if (this.idBuilder_ == null) {
                this.id_ = null;
                onChanged();
            } else {
                this.id_ = null;
                this.idBuilder_ = null;
            }
            return this;
        }

        public Id.Builder getIdBuilder() {
            onChanged();
            return getIdFieldBuilder().getBuilder();
        }

        @Override // com.dirac.acs.datastore.ParamValueOrBuilder
        public IdOrBuilder getIdOrBuilder() {
            SingleFieldBuilderV3<Id, Id.Builder, IdOrBuilder> singleFieldBuilderV3 = this.idBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (IdOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Id id = this.id_;
            if (id != null) {
                return id;
            }
            return Id.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Id, Id.Builder, IdOrBuilder> getIdFieldBuilder() {
            if (this.idBuilder_ == null) {
                this.idBuilder_ = new SingleFieldBuilderV3<>(getId(), getParentForChildren(), isClean());
                this.id_ = null;
            }
            return this.idBuilder_;
        }

        @Override // com.dirac.acs.datastore.ParamValueOrBuilder
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

        @Override // com.dirac.acs.datastore.ParamValueOrBuilder
        public boolean getUpdated() {
            return this.updated_;
        }

        public Builder setUpdated(boolean value) {
            this.updated_ = value;
            onChanged();
            return this;
        }

        public Builder clearUpdated() {
            this.updated_ = false;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m505setUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.setUnknownFields(unknownFields);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m499mergeUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.mergeUnknownFields(unknownFields);
        }
    }

    public static ParamValue getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ParamValue> parser() {
        return PARSER;
    }

    public Parser<ParamValue> getParserForType() {
        return PARSER;
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ParamValue m461getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
