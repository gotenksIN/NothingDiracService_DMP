package com.dirac.acs.datastore;

import com.dirac.acs.datastore.ParamValue;
import com.google.protobuf.AbstractMessageLite;
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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class Configuration extends GeneratedMessageV3 implements ConfigurationOrBuilder {
    public static final int ACTIVETIME_FIELD_NUMBER = 6;
    public static final int ACTIVE_FIELD_NUMBER = 5;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int OUTPUTTYPE_FIELD_NUMBER = 2;
    public static final int STREAMTYPE_FIELD_NUMBER = 3;
    public static final int VALUES_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private Timestamp activeTime_;
    private boolean active_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private volatile Object outputType_;
    private volatile Object streamType_;
    private List<ParamValue> values_;
    private static final Configuration DEFAULT_INSTANCE = new Configuration();
    private static final Parser<Configuration> PARSER = new AbstractParser<Configuration>() { // from class: com.dirac.acs.datastore.Configuration.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Configuration m155parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Configuration(input, extensionRegistry);
        }
    };

    private Configuration(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Configuration() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.outputType_ = "";
        this.streamType_ = "";
        this.values_ = Collections.emptyList();
    }

    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
        return new Configuration();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0011. Please report as an issue. */
    private Configuration(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
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
                            String s = input.readStringRequireUtf8();
                            this.name_ = s;
                        case 18:
                            String s2 = input.readStringRequireUtf8();
                            this.outputType_ = s2;
                        case 26:
                            String s3 = input.readStringRequireUtf8();
                            this.streamType_ = s3;
                        case 34:
                            if ((mutable_bitField0_ & 1) == 0) {
                                this.values_ = new ArrayList();
                                mutable_bitField0_ |= 1;
                            }
                            this.values_.add((ParamValue) input.readMessage(ParamValue.parser(), extensionRegistry));
                        case 40:
                            this.active_ = input.readBool();
                        case 50:
                            Timestamp timestamp = this.activeTime_;
                            Timestamp.Builder subBuilder = timestamp != null ? timestamp.toBuilder() : null;
                            Timestamp readMessage = input.readMessage(Timestamp.parser(), extensionRegistry);
                            this.activeTime_ = readMessage;
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(readMessage);
                                this.activeTime_ = subBuilder.buildPartial();
                            }
                        default:
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                if ((mutable_bitField0_ & 1) != 0) {
                    this.values_ = Collections.unmodifiableList(this.values_);
                }
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SettingsOuterClass.internal_static_com_dirac_acs_datastore_Configuration_descriptor;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SettingsOuterClass.internal_static_com_dirac_acs_datastore_Configuration_fieldAccessorTable.ensureFieldAccessorsInitialized(Configuration.class, Builder.class);
    }

    @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
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

    @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
    public ByteString getNameBytes() {
        Object ref = this.name_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String) ref);
            this.name_ = b;
            return b;
        }
        return (ByteString) ref;
    }

    @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
    public String getOutputType() {
        Object ref = this.outputType_;
        if (ref instanceof String) {
            return (String) ref;
        }
        ByteString bs = (ByteString) ref;
        String s = bs.toStringUtf8();
        this.outputType_ = s;
        return s;
    }

    @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
    public ByteString getOutputTypeBytes() {
        Object ref = this.outputType_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String) ref);
            this.outputType_ = b;
            return b;
        }
        return (ByteString) ref;
    }

    @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
    public String getStreamType() {
        Object ref = this.streamType_;
        if (ref instanceof String) {
            return (String) ref;
        }
        ByteString bs = (ByteString) ref;
        String s = bs.toStringUtf8();
        this.streamType_ = s;
        return s;
    }

    @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
    public ByteString getStreamTypeBytes() {
        Object ref = this.streamType_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String) ref);
            this.streamType_ = b;
            return b;
        }
        return (ByteString) ref;
    }

    @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
    public List<ParamValue> getValuesList() {
        return this.values_;
    }

    @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
    public List<? extends ParamValueOrBuilder> getValuesOrBuilderList() {
        return this.values_;
    }

    @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
    public int getValuesCount() {
        return this.values_.size();
    }

    @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
    public ParamValue getValues(int index) {
        return this.values_.get(index);
    }

    @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
    public ParamValueOrBuilder getValuesOrBuilder(int index) {
        return this.values_.get(index);
    }

    @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
    public boolean getActive() {
        return this.active_;
    }

    @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
    public boolean hasActiveTime() {
        return this.activeTime_ != null;
    }

    @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
    public Timestamp getActiveTime() {
        Timestamp timestamp = this.activeTime_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
    public TimestampOrBuilder getActiveTimeOrBuilder() {
        return getActiveTime();
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
        if (!getOutputTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.outputType_);
        }
        if (!getStreamTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 3, this.streamType_);
        }
        for (int i = 0; i < this.values_.size(); i++) {
            output.writeMessage(4, this.values_.get(i));
        }
        boolean z = this.active_;
        if (z) {
            output.writeBool(5, z);
        }
        if (this.activeTime_ != null) {
            output.writeMessage(6, getActiveTime());
        }
        this.unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
        int size = this.memoizedSize;
        if (size != -1) {
            return size;
        }
        int size2 = getNameBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.name_);
        if (!getOutputTypeBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.outputType_);
        }
        if (!getStreamTypeBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(3, this.streamType_);
        }
        for (int i = 0; i < this.values_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.values_.get(i));
        }
        boolean z = this.active_;
        if (z) {
            size2 += CodedOutputStream.computeBoolSize(5, z);
        }
        if (this.activeTime_ != null) {
            size2 += CodedOutputStream.computeMessageSize(6, getActiveTime());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = size3;
        return size3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Configuration)) {
            return super.equals(obj);
        }
        Configuration other = (Configuration) obj;
        if (getName().equals(other.getName()) && getOutputType().equals(other.getOutputType()) && getStreamType().equals(other.getStreamType()) && getValuesList().equals(other.getValuesList()) && getActive() == other.getActive() && hasActiveTime() == other.hasActiveTime()) {
            return (!hasActiveTime() || getActiveTime().equals(other.getActiveTime())) && this.unknownFields.equals(other.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = (((((((((((((41 * 19) + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getOutputType().hashCode()) * 37) + 3) * 53) + getStreamType().hashCode();
        if (getValuesCount() > 0) {
            hash = (((hash * 37) + 4) * 53) + getValuesList().hashCode();
        }
        int hash2 = (((hash * 37) + 5) * 53) + Internal.hashBoolean(getActive());
        if (hasActiveTime()) {
            hash2 = (((hash2 * 37) + 6) * 53) + getActiveTime().hashCode();
        }
        int hash3 = (hash2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hash3;
        return hash3;
    }

    public static Configuration parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Configuration) PARSER.parseFrom(data);
    }

    public static Configuration parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Configuration) PARSER.parseFrom(data, extensionRegistry);
    }

    public static Configuration parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Configuration) PARSER.parseFrom(data);
    }

    public static Configuration parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Configuration) PARSER.parseFrom(data, extensionRegistry);
    }

    public static Configuration parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Configuration) PARSER.parseFrom(data);
    }

    public static Configuration parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Configuration) PARSER.parseFrom(data, extensionRegistry);
    }

    public static Configuration parseFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static Configuration parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static Configuration parseDelimitedFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static Configuration parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static Configuration parseFrom(CodedInputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static Configuration parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m152newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m154toBuilder();
    }

    public static Builder newBuilder(Configuration prototype) {
        return DEFAULT_INSTANCE.m154toBuilder().mergeFrom(prototype);
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m154toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m151newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ConfigurationOrBuilder {
        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> activeTimeBuilder_;
        private Timestamp activeTime_;
        private boolean active_;
        private int bitField0_;
        private Object name_;
        private Object outputType_;
        private Object streamType_;
        private RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> valuesBuilder_;
        private List<ParamValue> values_;

        public static final Descriptors.Descriptor getDescriptor() {
            return SettingsOuterClass.internal_static_com_dirac_acs_datastore_Configuration_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SettingsOuterClass.internal_static_com_dirac_acs_datastore_Configuration_fieldAccessorTable.ensureFieldAccessorsInitialized(Configuration.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.outputType_ = "";
            this.streamType_ = "";
            this.values_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            this.name_ = "";
            this.outputType_ = "";
            this.streamType_ = "";
            this.values_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Configuration.alwaysUseFieldBuilders) {
                getValuesFieldBuilder();
            }
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m165clear() {
            super.clear();
            this.name_ = "";
            this.outputType_ = "";
            this.streamType_ = "";
            RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.values_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.active_ = false;
            if (this.activeTimeBuilder_ == null) {
                this.activeTime_ = null;
            } else {
                this.activeTime_ = null;
                this.activeTimeBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return SettingsOuterClass.internal_static_com_dirac_acs_datastore_Configuration_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Configuration m178getDefaultInstanceForType() {
            return Configuration.getDefaultInstance();
        }

        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Configuration m159build() {
            Configuration result = m161buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Configuration m161buildPartial() {
            Configuration result = new Configuration(this);
            int i = this.bitField0_;
            result.name_ = this.name_;
            result.outputType_ = this.outputType_;
            result.streamType_ = this.streamType_;
            RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 1) != 0) {
                    this.values_ = Collections.unmodifiableList(this.values_);
                    this.bitField0_ &= -2;
                }
                result.values_ = this.values_;
            } else {
                result.values_ = repeatedFieldBuilderV3.build();
            }
            result.active_ = this.active_;
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.activeTimeBuilder_;
            if (singleFieldBuilderV3 == null) {
                result.activeTime_ = this.activeTime_;
            } else {
                result.activeTime_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return result;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m176clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m189setField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.setField(field, value);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m167clearField(Descriptors.FieldDescriptor field) {
            return (Builder) super.clearField(field);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m170clearOneof(Descriptors.OneofDescriptor oneof) {
            return (Builder) super.clearOneof(oneof);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m191setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
            return (Builder) super.setRepeatedField(field, index, value);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m157addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.addRepeatedField(field, value);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m183mergeFrom(Message other) {
            if (other instanceof Configuration) {
                return mergeFrom((Configuration) other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Configuration other) {
            if (other == Configuration.getDefaultInstance()) {
                return this;
            }
            if (!other.getName().isEmpty()) {
                this.name_ = other.name_;
                onChanged();
            }
            if (!other.getOutputType().isEmpty()) {
                this.outputType_ = other.outputType_;
                onChanged();
            }
            if (!other.getStreamType().isEmpty()) {
                this.streamType_ = other.streamType_;
                onChanged();
            }
            if (this.valuesBuilder_ == null) {
                if (!other.values_.isEmpty()) {
                    if (this.values_.isEmpty()) {
                        this.values_ = other.values_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureValuesIsMutable();
                        this.values_.addAll(other.values_);
                    }
                    onChanged();
                }
            } else if (!other.values_.isEmpty()) {
                if (this.valuesBuilder_.isEmpty()) {
                    this.valuesBuilder_.dispose();
                    RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> repeatedFieldBuilderV3 = null;
                    this.valuesBuilder_ = null;
                    this.values_ = other.values_;
                    this.bitField0_ &= -2;
                    if (Configuration.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getValuesFieldBuilder();
                    }
                    this.valuesBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.valuesBuilder_.addAllMessages(other.values_);
                }
            }
            if (other.getActive()) {
                setActive(other.getActive());
            }
            if (other.hasActiveTime()) {
                mergeActiveTime(other.getActiveTime());
            }
            m187mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m184mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Configuration parsedMessage = null;
            try {
                try {
                    parsedMessage = (Configuration) Configuration.PARSER.parsePartialFrom(input, extensionRegistry);
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

        @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
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

        @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
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
            this.name_ = Configuration.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            if (value != null) {
                Configuration.checkByteStringIsUtf8(value);
                this.name_ = value;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
        public String getOutputType() {
            Object ref = this.outputType_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString) ref;
                String s = bs.toStringUtf8();
                this.outputType_ = s;
                return s;
            }
            return (String) ref;
        }

        @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
        public ByteString getOutputTypeBytes() {
            Object ref = this.outputType_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.outputType_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        public Builder setOutputType(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.outputType_ = value;
            onChanged();
            return this;
        }

        public Builder clearOutputType() {
            this.outputType_ = Configuration.getDefaultInstance().getOutputType();
            onChanged();
            return this;
        }

        public Builder setOutputTypeBytes(ByteString value) {
            if (value != null) {
                Configuration.checkByteStringIsUtf8(value);
                this.outputType_ = value;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
        public String getStreamType() {
            Object ref = this.streamType_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString) ref;
                String s = bs.toStringUtf8();
                this.streamType_ = s;
                return s;
            }
            return (String) ref;
        }

        @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
        public ByteString getStreamTypeBytes() {
            Object ref = this.streamType_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.streamType_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        public Builder setStreamType(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.streamType_ = value;
            onChanged();
            return this;
        }

        public Builder clearStreamType() {
            this.streamType_ = Configuration.getDefaultInstance().getStreamType();
            onChanged();
            return this;
        }

        public Builder setStreamTypeBytes(ByteString value) {
            if (value != null) {
                Configuration.checkByteStringIsUtf8(value);
                this.streamType_ = value;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureValuesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.values_ = new ArrayList(this.values_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
        public List<ParamValue> getValuesList() {
            RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.values_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
        public int getValuesCount() {
            RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.values_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
        public ParamValue getValues(int index) {
            RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.values_.get(index);
            }
            return repeatedFieldBuilderV3.getMessage(index);
        }

        public Builder setValues(int index, ParamValue value) {
            RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureValuesIsMutable();
                this.values_.set(index, value);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(index, value);
            }
            return this;
        }

        public Builder setValues(int index, ParamValue.Builder builderForValue) {
            RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureValuesIsMutable();
                this.values_.set(index, builderForValue.m471build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(index, builderForValue.m471build());
            }
            return this;
        }

        public Builder addValues(ParamValue value) {
            RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureValuesIsMutable();
                this.values_.add(value);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(value);
            }
            return this;
        }

        public Builder addValues(int index, ParamValue value) {
            RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureValuesIsMutable();
                this.values_.add(index, value);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(index, value);
            }
            return this;
        }

        public Builder addValues(ParamValue.Builder builderForValue) {
            RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureValuesIsMutable();
                this.values_.add(builderForValue.m471build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builderForValue.m471build());
            }
            return this;
        }

        public Builder addValues(int index, ParamValue.Builder builderForValue) {
            RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureValuesIsMutable();
                this.values_.add(index, builderForValue.m471build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(index, builderForValue.m471build());
            }
            return this;
        }

        public Builder addAllValues(Iterable<? extends ParamValue> values) {
            RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureValuesIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.values_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(values);
            }
            return this;
        }

        public Builder clearValues() {
            RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.values_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeValues(int index) {
            RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureValuesIsMutable();
                this.values_.remove(index);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(index);
            }
            return this;
        }

        public ParamValue.Builder getValuesBuilder(int index) {
            return getValuesFieldBuilder().getBuilder(index);
        }

        @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
        public ParamValueOrBuilder getValuesOrBuilder(int index) {
            RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.values_.get(index);
            }
            return (ParamValueOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(index);
        }

        @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
        public List<? extends ParamValueOrBuilder> getValuesOrBuilderList() {
            RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.values_);
        }

        public ParamValue.Builder addValuesBuilder() {
            return getValuesFieldBuilder().addBuilder(ParamValue.getDefaultInstance());
        }

        public ParamValue.Builder addValuesBuilder(int index) {
            return getValuesFieldBuilder().addBuilder(index, ParamValue.getDefaultInstance());
        }

        public List<ParamValue.Builder> getValuesBuilderList() {
            return getValuesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> getValuesFieldBuilder() {
            if (this.valuesBuilder_ == null) {
                this.valuesBuilder_ = new RepeatedFieldBuilderV3<>(this.values_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.values_ = null;
            }
            return this.valuesBuilder_;
        }

        @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
        public boolean getActive() {
            return this.active_;
        }

        public Builder setActive(boolean value) {
            this.active_ = value;
            onChanged();
            return this;
        }

        public Builder clearActive() {
            this.active_ = false;
            onChanged();
            return this;
        }

        @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
        public boolean hasActiveTime() {
            return (this.activeTimeBuilder_ == null && this.activeTime_ == null) ? false : true;
        }

        @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
        public Timestamp getActiveTime() {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.activeTimeBuilder_;
            if (singleFieldBuilderV3 == null) {
                Timestamp timestamp = this.activeTime_;
                return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setActiveTime(Timestamp value) {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.activeTimeBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.activeTime_ = value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(value);
            }
            return this;
        }

        public Builder setActiveTime(Timestamp.Builder builderForValue) {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.activeTimeBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.activeTime_ = builderForValue.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeActiveTime(Timestamp value) {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.activeTimeBuilder_;
            if (singleFieldBuilderV3 == null) {
                Timestamp timestamp = this.activeTime_;
                if (timestamp != null) {
                    this.activeTime_ = Timestamp.newBuilder(timestamp).mergeFrom(value).buildPartial();
                } else {
                    this.activeTime_ = value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(value);
            }
            return this;
        }

        public Builder clearActiveTime() {
            if (this.activeTimeBuilder_ == null) {
                this.activeTime_ = null;
                onChanged();
            } else {
                this.activeTime_ = null;
                this.activeTimeBuilder_ = null;
            }
            return this;
        }

        public Timestamp.Builder getActiveTimeBuilder() {
            onChanged();
            return getActiveTimeFieldBuilder().getBuilder();
        }

        @Override // com.dirac.acs.datastore.ConfigurationOrBuilder
        public TimestampOrBuilder getActiveTimeOrBuilder() {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.activeTimeBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Timestamp timestamp = this.activeTime_;
            if (timestamp != null) {
                return timestamp;
            }
            return Timestamp.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> getActiveTimeFieldBuilder() {
            if (this.activeTimeBuilder_ == null) {
                this.activeTimeBuilder_ = new SingleFieldBuilderV3<>(getActiveTime(), getParentForChildren(), isClean());
                this.activeTime_ = null;
            }
            return this.activeTimeBuilder_;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m193setUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.setUnknownFields(unknownFields);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m187mergeUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.mergeUnknownFields(unknownFields);
        }
    }

    public static Configuration getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Configuration> parser() {
        return PARSER;
    }

    public Parser<Configuration> getParserForType() {
        return PARSER;
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Configuration m149getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
