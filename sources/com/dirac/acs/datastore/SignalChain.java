package com.dirac.acs.datastore;

import com.dirac.acs.datastore.ParamInfo;
import com.dirac.acs.datastore.Version;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public final class SignalChain extends GeneratedMessageV3 implements SignalChainOrBuilder {
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int PARAMETERS_FIELD_NUMBER = 4;
    public static final int RT_CORE_VERSION_FIELD_NUMBER = 2;
    public static final int SIGNAL_CHAIN_META_DATA_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private List<ParamInfo> parameters_;
    private Version rtCoreVersion_;
    private MapField<String, Value> signalChainMetaData_;
    private static final SignalChain DEFAULT_INSTANCE = new SignalChain();
    private static final Parser<SignalChain> PARSER = new AbstractParser<SignalChain>() { // from class: com.dirac.acs.datastore.SignalChain.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public SignalChain m676parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new SignalChain(input, extensionRegistry);
        }
    };

    private SignalChain(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private SignalChain() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.parameters_ = Collections.emptyList();
    }

    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
        return new SignalChain();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0011. Please report as an issue. */
    private SignalChain(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            Version version = this.rtCoreVersion_;
                            Version.Builder subBuilder = version != null ? version.m775toBuilder() : null;
                            Version version2 = (Version) input.readMessage(Version.parser(), extensionRegistry);
                            this.rtCoreVersion_ = version2;
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(version2);
                                this.rtCoreVersion_ = subBuilder.m782buildPartial();
                            }
                        case 26:
                            if ((mutable_bitField0_ & 1) == 0) {
                                this.signalChainMetaData_ = MapField.newMapField(SignalChainMetaDataDefaultEntryHolder.defaultEntry);
                                mutable_bitField0_ |= 1;
                            }
                            MapEntry<String, Value> signalChainMetaData__ = input.readMessage(SignalChainMetaDataDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
                            this.signalChainMetaData_.getMutableMap().put((String) signalChainMetaData__.getKey(), (Value) signalChainMetaData__.getValue());
                        case 34:
                            if ((mutable_bitField0_ & 2) == 0) {
                                this.parameters_ = new ArrayList();
                                mutable_bitField0_ |= 2;
                            }
                            this.parameters_.add((ParamInfo) input.readMessage(ParamInfo.parser(), extensionRegistry));
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
                if ((mutable_bitField0_ & 2) != 0) {
                    this.parameters_ = Collections.unmodifiableList(this.parameters_);
                }
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return DarInterface.internal_static_com_dirac_acs_datastore_SignalChain_descriptor;
    }

    protected MapField internalGetMapField(int number) {
        switch (number) {
            case 3:
                return internalGetSignalChainMetaData();
            default:
                throw new RuntimeException("Invalid map field number: " + number);
        }
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DarInterface.internal_static_com_dirac_acs_datastore_SignalChain_fieldAccessorTable.ensureFieldAccessorsInitialized(SignalChain.class, Builder.class);
    }

    @Override // com.dirac.acs.datastore.SignalChainOrBuilder
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

    @Override // com.dirac.acs.datastore.SignalChainOrBuilder
    public ByteString getNameBytes() {
        Object ref = this.name_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String) ref);
            this.name_ = b;
            return b;
        }
        return (ByteString) ref;
    }

    @Override // com.dirac.acs.datastore.SignalChainOrBuilder
    public boolean hasRtCoreVersion() {
        return this.rtCoreVersion_ != null;
    }

    @Override // com.dirac.acs.datastore.SignalChainOrBuilder
    public Version getRtCoreVersion() {
        Version version = this.rtCoreVersion_;
        return version == null ? Version.getDefaultInstance() : version;
    }

    @Override // com.dirac.acs.datastore.SignalChainOrBuilder
    public VersionOrBuilder getRtCoreVersionOrBuilder() {
        return getRtCoreVersion();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class SignalChainMetaDataDefaultEntryHolder {
        static final MapEntry<String, Value> defaultEntry = MapEntry.newDefaultInstance(DarInterface.internal_static_com_dirac_acs_datastore_SignalChain_SignalChainMetaDataEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, Value.getDefaultInstance());

        private SignalChainMetaDataDefaultEntryHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, Value> internalGetSignalChainMetaData() {
        MapField<String, Value> mapField = this.signalChainMetaData_;
        if (mapField == null) {
            return MapField.emptyMapField(SignalChainMetaDataDefaultEntryHolder.defaultEntry);
        }
        return mapField;
    }

    @Override // com.dirac.acs.datastore.SignalChainOrBuilder
    public int getSignalChainMetaDataCount() {
        return internalGetSignalChainMetaData().getMap().size();
    }

    @Override // com.dirac.acs.datastore.SignalChainOrBuilder
    public boolean containsSignalChainMetaData(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return internalGetSignalChainMetaData().getMap().containsKey(key);
    }

    @Override // com.dirac.acs.datastore.SignalChainOrBuilder
    @Deprecated
    public Map<String, Value> getSignalChainMetaData() {
        return getSignalChainMetaDataMap();
    }

    @Override // com.dirac.acs.datastore.SignalChainOrBuilder
    public Map<String, Value> getSignalChainMetaDataMap() {
        return internalGetSignalChainMetaData().getMap();
    }

    @Override // com.dirac.acs.datastore.SignalChainOrBuilder
    public Value getSignalChainMetaDataOrDefault(String key, Value defaultValue) {
        if (key == null) {
            throw new NullPointerException();
        }
        Map<String, Value> map = internalGetSignalChainMetaData().getMap();
        return map.containsKey(key) ? map.get(key) : defaultValue;
    }

    @Override // com.dirac.acs.datastore.SignalChainOrBuilder
    public Value getSignalChainMetaDataOrThrow(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        Map<String, Value> map = internalGetSignalChainMetaData().getMap();
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return map.get(key);
    }

    @Override // com.dirac.acs.datastore.SignalChainOrBuilder
    public List<ParamInfo> getParametersList() {
        return this.parameters_;
    }

    @Override // com.dirac.acs.datastore.SignalChainOrBuilder
    public List<? extends ParamInfoOrBuilder> getParametersOrBuilderList() {
        return this.parameters_;
    }

    @Override // com.dirac.acs.datastore.SignalChainOrBuilder
    public int getParametersCount() {
        return this.parameters_.size();
    }

    @Override // com.dirac.acs.datastore.SignalChainOrBuilder
    public ParamInfo getParameters(int index) {
        return this.parameters_.get(index);
    }

    @Override // com.dirac.acs.datastore.SignalChainOrBuilder
    public ParamInfoOrBuilder getParametersOrBuilder(int index) {
        return this.parameters_.get(index);
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
        if (this.rtCoreVersion_ != null) {
            output.writeMessage(2, getRtCoreVersion());
        }
        GeneratedMessageV3.serializeStringMapTo(output, internalGetSignalChainMetaData(), SignalChainMetaDataDefaultEntryHolder.defaultEntry, 3);
        for (int i = 0; i < this.parameters_.size(); i++) {
            output.writeMessage(4, this.parameters_.get(i));
        }
        this.unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
        int size = this.memoizedSize;
        if (size != -1) {
            return size;
        }
        int size2 = getNameBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.name_);
        if (this.rtCoreVersion_ != null) {
            size2 += CodedOutputStream.computeMessageSize(2, getRtCoreVersion());
        }
        for (Map.Entry<String, Value> entry : internalGetSignalChainMetaData().getMap().entrySet()) {
            MapEntry<String, Value> signalChainMetaData__ = SignalChainMetaDataDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build();
            size2 += CodedOutputStream.computeMessageSize(3, signalChainMetaData__);
        }
        for (int i = 0; i < this.parameters_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(4, this.parameters_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = size3;
        return size3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SignalChain)) {
            return super.equals(obj);
        }
        SignalChain other = (SignalChain) obj;
        if (getName().equals(other.getName()) && hasRtCoreVersion() == other.hasRtCoreVersion()) {
            return (!hasRtCoreVersion() || getRtCoreVersion().equals(other.getRtCoreVersion())) && internalGetSignalChainMetaData().equals(other.internalGetSignalChainMetaData()) && getParametersList().equals(other.getParametersList()) && this.unknownFields.equals(other.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = (((((41 * 19) + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
        if (hasRtCoreVersion()) {
            hash = (((hash * 37) + 2) * 53) + getRtCoreVersion().hashCode();
        }
        if (!internalGetSignalChainMetaData().getMap().isEmpty()) {
            hash = (((hash * 37) + 3) * 53) + internalGetSignalChainMetaData().hashCode();
        }
        if (getParametersCount() > 0) {
            hash = (((hash * 37) + 4) * 53) + getParametersList().hashCode();
        }
        int hash2 = (hash * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hash2;
        return hash2;
    }

    public static SignalChain parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (SignalChain) PARSER.parseFrom(data);
    }

    public static SignalChain parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SignalChain) PARSER.parseFrom(data, extensionRegistry);
    }

    public static SignalChain parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (SignalChain) PARSER.parseFrom(data);
    }

    public static SignalChain parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SignalChain) PARSER.parseFrom(data, extensionRegistry);
    }

    public static SignalChain parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (SignalChain) PARSER.parseFrom(data);
    }

    public static SignalChain parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SignalChain) PARSER.parseFrom(data, extensionRegistry);
    }

    public static SignalChain parseFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static SignalChain parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static SignalChain parseDelimitedFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static SignalChain parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static SignalChain parseFrom(CodedInputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static SignalChain parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m673newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m675toBuilder();
    }

    public static Builder newBuilder(SignalChain prototype) {
        return DEFAULT_INSTANCE.m675toBuilder().mergeFrom(prototype);
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m675toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m672newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SignalChainOrBuilder {
        private int bitField0_;
        private Object name_;
        private RepeatedFieldBuilderV3<ParamInfo, ParamInfo.Builder, ParamInfoOrBuilder> parametersBuilder_;
        private List<ParamInfo> parameters_;
        private SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> rtCoreVersionBuilder_;
        private Version rtCoreVersion_;
        private MapField<String, Value> signalChainMetaData_;

        public static final Descriptors.Descriptor getDescriptor() {
            return DarInterface.internal_static_com_dirac_acs_datastore_SignalChain_descriptor;
        }

        protected MapField internalGetMapField(int number) {
            switch (number) {
                case 3:
                    return internalGetSignalChainMetaData();
                default:
                    throw new RuntimeException("Invalid map field number: " + number);
            }
        }

        protected MapField internalGetMutableMapField(int number) {
            switch (number) {
                case 3:
                    return internalGetMutableSignalChainMetaData();
                default:
                    throw new RuntimeException("Invalid map field number: " + number);
            }
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DarInterface.internal_static_com_dirac_acs_datastore_SignalChain_fieldAccessorTable.ensureFieldAccessorsInitialized(SignalChain.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.parameters_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            this.name_ = "";
            this.parameters_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (SignalChain.alwaysUseFieldBuilders) {
                getParametersFieldBuilder();
            }
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m686clear() {
            super.clear();
            this.name_ = "";
            if (this.rtCoreVersionBuilder_ == null) {
                this.rtCoreVersion_ = null;
            } else {
                this.rtCoreVersion_ = null;
                this.rtCoreVersionBuilder_ = null;
            }
            internalGetMutableSignalChainMetaData().clear();
            RepeatedFieldBuilderV3<ParamInfo, ParamInfo.Builder, ParamInfoOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.parameters_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return DarInterface.internal_static_com_dirac_acs_datastore_SignalChain_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SignalChain m699getDefaultInstanceForType() {
            return SignalChain.getDefaultInstance();
        }

        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SignalChain m680build() {
            SignalChain result = m682buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public SignalChain m682buildPartial() {
            SignalChain result = new SignalChain(this);
            int i = this.bitField0_;
            result.name_ = this.name_;
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.rtCoreVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                result.rtCoreVersion_ = this.rtCoreVersion_;
            } else {
                result.rtCoreVersion_ = singleFieldBuilderV3.build();
            }
            result.signalChainMetaData_ = internalGetSignalChainMetaData();
            result.signalChainMetaData_.makeImmutable();
            RepeatedFieldBuilderV3<ParamInfo, ParamInfo.Builder, ParamInfoOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 2) != 0) {
                    this.parameters_ = Collections.unmodifiableList(this.parameters_);
                    this.bitField0_ &= -3;
                }
                result.parameters_ = this.parameters_;
            } else {
                result.parameters_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return result;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m697clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m710setField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.setField(field, value);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m688clearField(Descriptors.FieldDescriptor field) {
            return (Builder) super.clearField(field);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m691clearOneof(Descriptors.OneofDescriptor oneof) {
            return (Builder) super.clearOneof(oneof);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m712setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
            return (Builder) super.setRepeatedField(field, index, value);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m678addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.addRepeatedField(field, value);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m704mergeFrom(Message other) {
            if (other instanceof SignalChain) {
                return mergeFrom((SignalChain) other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(SignalChain other) {
            if (other == SignalChain.getDefaultInstance()) {
                return this;
            }
            if (!other.getName().isEmpty()) {
                this.name_ = other.name_;
                onChanged();
            }
            if (other.hasRtCoreVersion()) {
                mergeRtCoreVersion(other.getRtCoreVersion());
            }
            internalGetMutableSignalChainMetaData().mergeFrom(other.internalGetSignalChainMetaData());
            if (this.parametersBuilder_ == null) {
                if (!other.parameters_.isEmpty()) {
                    if (this.parameters_.isEmpty()) {
                        this.parameters_ = other.parameters_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureParametersIsMutable();
                        this.parameters_.addAll(other.parameters_);
                    }
                    onChanged();
                }
            } else if (!other.parameters_.isEmpty()) {
                if (this.parametersBuilder_.isEmpty()) {
                    this.parametersBuilder_.dispose();
                    RepeatedFieldBuilderV3<ParamInfo, ParamInfo.Builder, ParamInfoOrBuilder> repeatedFieldBuilderV3 = null;
                    this.parametersBuilder_ = null;
                    this.parameters_ = other.parameters_;
                    this.bitField0_ &= -3;
                    if (SignalChain.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getParametersFieldBuilder();
                    }
                    this.parametersBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.parametersBuilder_.addAllMessages(other.parameters_);
                }
            }
            m708mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m705mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            SignalChain parsedMessage = null;
            try {
                try {
                    parsedMessage = (SignalChain) SignalChain.PARSER.parsePartialFrom(input, extensionRegistry);
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

        @Override // com.dirac.acs.datastore.SignalChainOrBuilder
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

        @Override // com.dirac.acs.datastore.SignalChainOrBuilder
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
            this.name_ = SignalChain.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            if (value != null) {
                SignalChain.checkByteStringIsUtf8(value);
                this.name_ = value;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.dirac.acs.datastore.SignalChainOrBuilder
        public boolean hasRtCoreVersion() {
            return (this.rtCoreVersionBuilder_ == null && this.rtCoreVersion_ == null) ? false : true;
        }

        @Override // com.dirac.acs.datastore.SignalChainOrBuilder
        public Version getRtCoreVersion() {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.rtCoreVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                Version version = this.rtCoreVersion_;
                return version == null ? Version.getDefaultInstance() : version;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setRtCoreVersion(Version value) {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.rtCoreVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.rtCoreVersion_ = value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(value);
            }
            return this;
        }

        public Builder setRtCoreVersion(Version.Builder builderForValue) {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.rtCoreVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.rtCoreVersion_ = builderForValue.m780build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builderForValue.m780build());
            }
            return this;
        }

        public Builder mergeRtCoreVersion(Version value) {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.rtCoreVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                Version version = this.rtCoreVersion_;
                if (version != null) {
                    this.rtCoreVersion_ = Version.newBuilder(version).mergeFrom(value).m782buildPartial();
                } else {
                    this.rtCoreVersion_ = value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(value);
            }
            return this;
        }

        public Builder clearRtCoreVersion() {
            if (this.rtCoreVersionBuilder_ == null) {
                this.rtCoreVersion_ = null;
                onChanged();
            } else {
                this.rtCoreVersion_ = null;
                this.rtCoreVersionBuilder_ = null;
            }
            return this;
        }

        public Version.Builder getRtCoreVersionBuilder() {
            onChanged();
            return getRtCoreVersionFieldBuilder().getBuilder();
        }

        @Override // com.dirac.acs.datastore.SignalChainOrBuilder
        public VersionOrBuilder getRtCoreVersionOrBuilder() {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.rtCoreVersionBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (VersionOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Version version = this.rtCoreVersion_;
            if (version != null) {
                return version;
            }
            return Version.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> getRtCoreVersionFieldBuilder() {
            if (this.rtCoreVersionBuilder_ == null) {
                this.rtCoreVersionBuilder_ = new SingleFieldBuilderV3<>(getRtCoreVersion(), getParentForChildren(), isClean());
                this.rtCoreVersion_ = null;
            }
            return this.rtCoreVersionBuilder_;
        }

        private MapField<String, Value> internalGetSignalChainMetaData() {
            MapField<String, Value> mapField = this.signalChainMetaData_;
            if (mapField == null) {
                return MapField.emptyMapField(SignalChainMetaDataDefaultEntryHolder.defaultEntry);
            }
            return mapField;
        }

        private MapField<String, Value> internalGetMutableSignalChainMetaData() {
            onChanged();
            if (this.signalChainMetaData_ == null) {
                this.signalChainMetaData_ = MapField.newMapField(SignalChainMetaDataDefaultEntryHolder.defaultEntry);
            }
            if (!this.signalChainMetaData_.isMutable()) {
                this.signalChainMetaData_ = this.signalChainMetaData_.copy();
            }
            return this.signalChainMetaData_;
        }

        @Override // com.dirac.acs.datastore.SignalChainOrBuilder
        public int getSignalChainMetaDataCount() {
            return internalGetSignalChainMetaData().getMap().size();
        }

        @Override // com.dirac.acs.datastore.SignalChainOrBuilder
        public boolean containsSignalChainMetaData(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            return internalGetSignalChainMetaData().getMap().containsKey(key);
        }

        @Override // com.dirac.acs.datastore.SignalChainOrBuilder
        @Deprecated
        public Map<String, Value> getSignalChainMetaData() {
            return getSignalChainMetaDataMap();
        }

        @Override // com.dirac.acs.datastore.SignalChainOrBuilder
        public Map<String, Value> getSignalChainMetaDataMap() {
            return internalGetSignalChainMetaData().getMap();
        }

        @Override // com.dirac.acs.datastore.SignalChainOrBuilder
        public Value getSignalChainMetaDataOrDefault(String key, Value defaultValue) {
            if (key == null) {
                throw new NullPointerException();
            }
            Map<String, Value> map = internalGetSignalChainMetaData().getMap();
            return map.containsKey(key) ? map.get(key) : defaultValue;
        }

        @Override // com.dirac.acs.datastore.SignalChainOrBuilder
        public Value getSignalChainMetaDataOrThrow(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            Map<String, Value> map = internalGetSignalChainMetaData().getMap();
            if (!map.containsKey(key)) {
                throw new IllegalArgumentException();
            }
            return map.get(key);
        }

        public Builder clearSignalChainMetaData() {
            internalGetMutableSignalChainMetaData().getMutableMap().clear();
            return this;
        }

        public Builder removeSignalChainMetaData(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            internalGetMutableSignalChainMetaData().getMutableMap().remove(key);
            return this;
        }

        @Deprecated
        public Map<String, Value> getMutableSignalChainMetaData() {
            return internalGetMutableSignalChainMetaData().getMutableMap();
        }

        public Builder putSignalChainMetaData(String key, Value value) {
            if (key == null) {
                throw new NullPointerException();
            }
            if (value == null) {
                throw new NullPointerException();
            }
            internalGetMutableSignalChainMetaData().getMutableMap().put(key, value);
            return this;
        }

        public Builder putAllSignalChainMetaData(Map<String, Value> values) {
            internalGetMutableSignalChainMetaData().getMutableMap().putAll(values);
            return this;
        }

        private void ensureParametersIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.parameters_ = new ArrayList(this.parameters_);
                this.bitField0_ |= 2;
            }
        }

        @Override // com.dirac.acs.datastore.SignalChainOrBuilder
        public List<ParamInfo> getParametersList() {
            RepeatedFieldBuilderV3<ParamInfo, ParamInfo.Builder, ParamInfoOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.parameters_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.dirac.acs.datastore.SignalChainOrBuilder
        public int getParametersCount() {
            RepeatedFieldBuilderV3<ParamInfo, ParamInfo.Builder, ParamInfoOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.parameters_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.dirac.acs.datastore.SignalChainOrBuilder
        public ParamInfo getParameters(int index) {
            RepeatedFieldBuilderV3<ParamInfo, ParamInfo.Builder, ParamInfoOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.parameters_.get(index);
            }
            return repeatedFieldBuilderV3.getMessage(index);
        }

        public Builder setParameters(int index, ParamInfo value) {
            RepeatedFieldBuilderV3<ParamInfo, ParamInfo.Builder, ParamInfoOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureParametersIsMutable();
                this.parameters_.set(index, value);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(index, value);
            }
            return this;
        }

        public Builder setParameters(int index, ParamInfo.Builder builderForValue) {
            RepeatedFieldBuilderV3<ParamInfo, ParamInfo.Builder, ParamInfoOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureParametersIsMutable();
                this.parameters_.set(index, builderForValue.m370build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(index, builderForValue.m370build());
            }
            return this;
        }

        public Builder addParameters(ParamInfo value) {
            RepeatedFieldBuilderV3<ParamInfo, ParamInfo.Builder, ParamInfoOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureParametersIsMutable();
                this.parameters_.add(value);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(value);
            }
            return this;
        }

        public Builder addParameters(int index, ParamInfo value) {
            RepeatedFieldBuilderV3<ParamInfo, ParamInfo.Builder, ParamInfoOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureParametersIsMutable();
                this.parameters_.add(index, value);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(index, value);
            }
            return this;
        }

        public Builder addParameters(ParamInfo.Builder builderForValue) {
            RepeatedFieldBuilderV3<ParamInfo, ParamInfo.Builder, ParamInfoOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureParametersIsMutable();
                this.parameters_.add(builderForValue.m370build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builderForValue.m370build());
            }
            return this;
        }

        public Builder addParameters(int index, ParamInfo.Builder builderForValue) {
            RepeatedFieldBuilderV3<ParamInfo, ParamInfo.Builder, ParamInfoOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureParametersIsMutable();
                this.parameters_.add(index, builderForValue.m370build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(index, builderForValue.m370build());
            }
            return this;
        }

        public Builder addAllParameters(Iterable<? extends ParamInfo> values) {
            RepeatedFieldBuilderV3<ParamInfo, ParamInfo.Builder, ParamInfoOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureParametersIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.parameters_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(values);
            }
            return this;
        }

        public Builder clearParameters() {
            RepeatedFieldBuilderV3<ParamInfo, ParamInfo.Builder, ParamInfoOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.parameters_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeParameters(int index) {
            RepeatedFieldBuilderV3<ParamInfo, ParamInfo.Builder, ParamInfoOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureParametersIsMutable();
                this.parameters_.remove(index);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(index);
            }
            return this;
        }

        public ParamInfo.Builder getParametersBuilder(int index) {
            return getParametersFieldBuilder().getBuilder(index);
        }

        @Override // com.dirac.acs.datastore.SignalChainOrBuilder
        public ParamInfoOrBuilder getParametersOrBuilder(int index) {
            RepeatedFieldBuilderV3<ParamInfo, ParamInfo.Builder, ParamInfoOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.parameters_.get(index);
            }
            return (ParamInfoOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(index);
        }

        @Override // com.dirac.acs.datastore.SignalChainOrBuilder
        public List<? extends ParamInfoOrBuilder> getParametersOrBuilderList() {
            RepeatedFieldBuilderV3<ParamInfo, ParamInfo.Builder, ParamInfoOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.parameters_);
        }

        public ParamInfo.Builder addParametersBuilder() {
            return getParametersFieldBuilder().addBuilder(ParamInfo.getDefaultInstance());
        }

        public ParamInfo.Builder addParametersBuilder(int index) {
            return getParametersFieldBuilder().addBuilder(index, ParamInfo.getDefaultInstance());
        }

        public List<ParamInfo.Builder> getParametersBuilderList() {
            return getParametersFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<ParamInfo, ParamInfo.Builder, ParamInfoOrBuilder> getParametersFieldBuilder() {
            if (this.parametersBuilder_ == null) {
                this.parametersBuilder_ = new RepeatedFieldBuilderV3<>(this.parameters_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.parameters_ = null;
            }
            return this.parametersBuilder_;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m714setUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.setUnknownFields(unknownFields);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m708mergeUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.mergeUnknownFields(unknownFields);
        }
    }

    public static SignalChain getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SignalChain> parser() {
        return PARSER;
    }

    public Parser<SignalChain> getParserForType() {
        return PARSER;
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public SignalChain m670getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
