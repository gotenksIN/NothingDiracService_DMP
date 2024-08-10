package com.dirac.acs.datastore;

import com.dirac.acs.datastore.Id;
import com.dirac.acs.datastore.ParamEnumValue;
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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class ParamInfo extends GeneratedMessageV3 implements ParamInfoOrBuilder {
    public static final int DEFAULT_FIELD_NUMBER = 6;
    public static final int DESCRIPTION_FIELD_NUMBER = 3;
    public static final int ENUM_VALUES_FIELD_NUMBER = 9;
    public static final int FORMATTING_FIELD_NUMBER = 11;
    public static final int ID_FIELD_NUMBER = 1;
    public static final int MAX_FIELD_NUMBER = 5;
    public static final int MIN_FIELD_NUMBER = 4;
    public static final int NAME_FIELD_NUMBER = 2;
    public static final int STEP_SIZE_FIELD_NUMBER = 7;
    public static final int TYPE_FIELD_NUMBER = 8;
    public static final int UNIT_FIELD_NUMBER = 10;
    private static final long serialVersionUID = 0;
    private double default_;
    private volatile Object description_;
    private List<ParamEnumValue> enumValues_;
    private volatile Object formatting_;
    private Id id_;
    private double max_;
    private byte memoizedIsInitialized;
    private double min_;
    private volatile Object name_;
    private double stepSize_;
    private int type_;
    private volatile Object unit_;
    private static final ParamInfo DEFAULT_INSTANCE = new ParamInfo();
    private static final Parser<ParamInfo> PARSER = new AbstractParser<ParamInfo>() { // from class: com.dirac.acs.datastore.ParamInfo.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ParamInfo m366parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new ParamInfo(input, extensionRegistry);
        }
    };

    private ParamInfo(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ParamInfo() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.description_ = "";
        this.type_ = 0;
        this.enumValues_ = Collections.emptyList();
        this.unit_ = "";
        this.formatting_ = "";
    }

    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
        return new ParamInfo();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0011. Please report as an issue. */
    private ParamInfo(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            case 18:
                                String s = input.readStringRequireUtf8();
                                this.name_ = s;
                            case 26:
                                String s2 = input.readStringRequireUtf8();
                                this.description_ = s2;
                            case 33:
                                this.min_ = input.readDouble();
                            case 41:
                                this.max_ = input.readDouble();
                            case 49:
                                this.default_ = input.readDouble();
                            case 57:
                                this.stepSize_ = input.readDouble();
                            case 64:
                                int rawValue = input.readEnum();
                                this.type_ = rawValue;
                            case 74:
                                if ((mutable_bitField0_ & 1) == 0) {
                                    this.enumValues_ = new ArrayList();
                                    mutable_bitField0_ |= 1;
                                }
                                this.enumValues_.add((ParamEnumValue) input.readMessage(ParamEnumValue.parser(), extensionRegistry));
                            case 82:
                                String s3 = input.readStringRequireUtf8();
                                this.unit_ = s3;
                            case 90:
                                String s4 = input.readStringRequireUtf8();
                                this.formatting_ = s4;
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
                if ((mutable_bitField0_ & 1) != 0) {
                    this.enumValues_ = Collections.unmodifiableList(this.enumValues_);
                }
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamInfo_descriptor;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ParamInfo.class, Builder.class);
    }

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
    public boolean hasId() {
        return this.id_ != null;
    }

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
    public Id getId() {
        Id id = this.id_;
        return id == null ? Id.getDefaultInstance() : id;
    }

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
    public IdOrBuilder getIdOrBuilder() {
        return getId();
    }

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
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

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
    public ByteString getNameBytes() {
        Object ref = this.name_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String) ref);
            this.name_ = b;
            return b;
        }
        return (ByteString) ref;
    }

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
    public String getDescription() {
        Object ref = this.description_;
        if (ref instanceof String) {
            return (String) ref;
        }
        ByteString bs = (ByteString) ref;
        String s = bs.toStringUtf8();
        this.description_ = s;
        return s;
    }

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
    public ByteString getDescriptionBytes() {
        Object ref = this.description_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String) ref);
            this.description_ = b;
            return b;
        }
        return (ByteString) ref;
    }

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
    public double getMin() {
        return this.min_;
    }

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
    public double getMax() {
        return this.max_;
    }

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
    public double getDefault() {
        return this.default_;
    }

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
    public double getStepSize() {
        return this.stepSize_;
    }

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
    public int getTypeValue() {
        return this.type_;
    }

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
    public ParamType getType() {
        ParamType result = ParamType.valueOf(this.type_);
        return result == null ? ParamType.UNRECOGNIZED : result;
    }

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
    public List<ParamEnumValue> getEnumValuesList() {
        return this.enumValues_;
    }

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
    public List<? extends ParamEnumValueOrBuilder> getEnumValuesOrBuilderList() {
        return this.enumValues_;
    }

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
    public int getEnumValuesCount() {
        return this.enumValues_.size();
    }

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
    public ParamEnumValue getEnumValues(int index) {
        return this.enumValues_.get(index);
    }

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
    public ParamEnumValueOrBuilder getEnumValuesOrBuilder(int index) {
        return this.enumValues_.get(index);
    }

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
    public String getUnit() {
        Object ref = this.unit_;
        if (ref instanceof String) {
            return (String) ref;
        }
        ByteString bs = (ByteString) ref;
        String s = bs.toStringUtf8();
        this.unit_ = s;
        return s;
    }

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
    public ByteString getUnitBytes() {
        Object ref = this.unit_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String) ref);
            this.unit_ = b;
            return b;
        }
        return (ByteString) ref;
    }

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
    public String getFormatting() {
        Object ref = this.formatting_;
        if (ref instanceof String) {
            return (String) ref;
        }
        ByteString bs = (ByteString) ref;
        String s = bs.toStringUtf8();
        this.formatting_ = s;
        return s;
    }

    @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
    public ByteString getFormattingBytes() {
        Object ref = this.formatting_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String) ref);
            this.formatting_ = b;
            return b;
        }
        return (ByteString) ref;
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
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.name_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 3, this.description_);
        }
        double d = this.min_;
        if (d != 0.0d) {
            output.writeDouble(4, d);
        }
        double d2 = this.max_;
        if (d2 != 0.0d) {
            output.writeDouble(5, d2);
        }
        double d3 = this.default_;
        if (d3 != 0.0d) {
            output.writeDouble(6, d3);
        }
        double d4 = this.stepSize_;
        if (d4 != 0.0d) {
            output.writeDouble(7, d4);
        }
        if (this.type_ != ParamType.PARAM_TYPE_LINEAR.getNumber()) {
            output.writeEnum(8, this.type_);
        }
        for (int i = 0; i < this.enumValues_.size(); i++) {
            output.writeMessage(9, this.enumValues_.get(i));
        }
        if (!getUnitBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 10, this.unit_);
        }
        if (!getFormattingBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 11, this.formatting_);
        }
        this.unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
        int size = this.memoizedSize;
        if (size != -1) {
            return size;
        }
        int size2 = this.id_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getId()) : 0;
        if (!getNameBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.name_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(3, this.description_);
        }
        double d = this.min_;
        if (d != 0.0d) {
            size2 += CodedOutputStream.computeDoubleSize(4, d);
        }
        double d2 = this.max_;
        if (d2 != 0.0d) {
            size2 += CodedOutputStream.computeDoubleSize(5, d2);
        }
        double d3 = this.default_;
        if (d3 != 0.0d) {
            size2 += CodedOutputStream.computeDoubleSize(6, d3);
        }
        double d4 = this.stepSize_;
        if (d4 != 0.0d) {
            size2 += CodedOutputStream.computeDoubleSize(7, d4);
        }
        if (this.type_ != ParamType.PARAM_TYPE_LINEAR.getNumber()) {
            size2 += CodedOutputStream.computeEnumSize(8, this.type_);
        }
        for (int i = 0; i < this.enumValues_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(9, this.enumValues_.get(i));
        }
        if (!getUnitBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(10, this.unit_);
        }
        if (!getFormattingBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(11, this.formatting_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = size3;
        return size3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ParamInfo)) {
            return super.equals(obj);
        }
        ParamInfo other = (ParamInfo) obj;
        if (hasId() != other.hasId()) {
            return false;
        }
        return (!hasId() || getId().equals(other.getId())) && getName().equals(other.getName()) && getDescription().equals(other.getDescription()) && Double.doubleToLongBits(getMin()) == Double.doubleToLongBits(other.getMin()) && Double.doubleToLongBits(getMax()) == Double.doubleToLongBits(other.getMax()) && Double.doubleToLongBits(getDefault()) == Double.doubleToLongBits(other.getDefault()) && Double.doubleToLongBits(getStepSize()) == Double.doubleToLongBits(other.getStepSize()) && this.type_ == other.type_ && getEnumValuesList().equals(other.getEnumValuesList()) && getUnit().equals(other.getUnit()) && getFormatting().equals(other.getFormatting()) && this.unknownFields.equals(other.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = (41 * 19) + getDescriptor().hashCode();
        if (hasId()) {
            hash = (((hash * 37) + 1) * 53) + getId().hashCode();
        }
        int hash2 = (((((((((((((((((((((((((((hash * 37) + 2) * 53) + getName().hashCode()) * 37) + 3) * 53) + getDescription().hashCode()) * 37) + 4) * 53) + Internal.hashLong(Double.doubleToLongBits(getMin()))) * 37) + 5) * 53) + Internal.hashLong(Double.doubleToLongBits(getMax()))) * 37) + 6) * 53) + Internal.hashLong(Double.doubleToLongBits(getDefault()))) * 37) + 7) * 53) + Internal.hashLong(Double.doubleToLongBits(getStepSize()))) * 37) + 8) * 53) + this.type_;
        if (getEnumValuesCount() > 0) {
            hash2 = (((hash2 * 37) + 9) * 53) + getEnumValuesList().hashCode();
        }
        int hash3 = (((((((((hash2 * 37) + 10) * 53) + getUnit().hashCode()) * 37) + 11) * 53) + getFormatting().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hash3;
        return hash3;
    }

    public static ParamInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (ParamInfo) PARSER.parseFrom(data);
    }

    public static ParamInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ParamInfo) PARSER.parseFrom(data, extensionRegistry);
    }

    public static ParamInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ParamInfo) PARSER.parseFrom(data);
    }

    public static ParamInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ParamInfo) PARSER.parseFrom(data, extensionRegistry);
    }

    public static ParamInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ParamInfo) PARSER.parseFrom(data);
    }

    public static ParamInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ParamInfo) PARSER.parseFrom(data, extensionRegistry);
    }

    public static ParamInfo parseFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static ParamInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static ParamInfo parseDelimitedFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static ParamInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static ParamInfo parseFrom(CodedInputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static ParamInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m363newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m365toBuilder();
    }

    public static Builder newBuilder(ParamInfo prototype) {
        return DEFAULT_INSTANCE.m365toBuilder().mergeFrom(prototype);
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m365toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m362newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ParamInfoOrBuilder {
        private int bitField0_;
        private double default_;
        private Object description_;
        private RepeatedFieldBuilderV3<ParamEnumValue, ParamEnumValue.Builder, ParamEnumValueOrBuilder> enumValuesBuilder_;
        private List<ParamEnumValue> enumValues_;
        private Object formatting_;
        private SingleFieldBuilderV3<Id, Id.Builder, IdOrBuilder> idBuilder_;
        private Id id_;
        private double max_;
        private double min_;
        private Object name_;
        private double stepSize_;
        private int type_;
        private Object unit_;

        public static final Descriptors.Descriptor getDescriptor() {
            return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamInfo_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ParamInfo.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.description_ = "";
            this.type_ = 0;
            this.enumValues_ = Collections.emptyList();
            this.unit_ = "";
            this.formatting_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            this.name_ = "";
            this.description_ = "";
            this.type_ = 0;
            this.enumValues_ = Collections.emptyList();
            this.unit_ = "";
            this.formatting_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (ParamInfo.alwaysUseFieldBuilders) {
                getEnumValuesFieldBuilder();
            }
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m376clear() {
            super.clear();
            if (this.idBuilder_ == null) {
                this.id_ = null;
            } else {
                this.id_ = null;
                this.idBuilder_ = null;
            }
            this.name_ = "";
            this.description_ = "";
            this.min_ = 0.0d;
            this.max_ = 0.0d;
            this.default_ = 0.0d;
            this.stepSize_ = 0.0d;
            this.type_ = 0;
            RepeatedFieldBuilderV3<ParamEnumValue, ParamEnumValue.Builder, ParamEnumValueOrBuilder> repeatedFieldBuilderV3 = this.enumValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.enumValues_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.unit_ = "";
            this.formatting_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamInfo_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ParamInfo m389getDefaultInstanceForType() {
            return ParamInfo.getDefaultInstance();
        }

        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ParamInfo m370build() {
            ParamInfo result = m372buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ParamInfo m372buildPartial() {
            ParamInfo result = new ParamInfo(this);
            int i = this.bitField0_;
            SingleFieldBuilderV3<Id, Id.Builder, IdOrBuilder> singleFieldBuilderV3 = this.idBuilder_;
            if (singleFieldBuilderV3 == null) {
                result.id_ = this.id_;
            } else {
                result.id_ = singleFieldBuilderV3.build();
            }
            result.name_ = this.name_;
            result.description_ = this.description_;
            result.min_ = this.min_;
            result.max_ = this.max_;
            result.default_ = this.default_;
            result.stepSize_ = this.stepSize_;
            result.type_ = this.type_;
            RepeatedFieldBuilderV3<ParamEnumValue, ParamEnumValue.Builder, ParamEnumValueOrBuilder> repeatedFieldBuilderV3 = this.enumValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 1) != 0) {
                    this.enumValues_ = Collections.unmodifiableList(this.enumValues_);
                    this.bitField0_ &= -2;
                }
                result.enumValues_ = this.enumValues_;
            } else {
                result.enumValues_ = repeatedFieldBuilderV3.build();
            }
            result.unit_ = this.unit_;
            result.formatting_ = this.formatting_;
            onBuilt();
            return result;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m387clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m400setField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.setField(field, value);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m378clearField(Descriptors.FieldDescriptor field) {
            return (Builder) super.clearField(field);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m381clearOneof(Descriptors.OneofDescriptor oneof) {
            return (Builder) super.clearOneof(oneof);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m402setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
            return (Builder) super.setRepeatedField(field, index, value);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m368addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.addRepeatedField(field, value);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m394mergeFrom(Message other) {
            if (other instanceof ParamInfo) {
                return mergeFrom((ParamInfo) other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(ParamInfo other) {
            if (other == ParamInfo.getDefaultInstance()) {
                return this;
            }
            if (other.hasId()) {
                mergeId(other.getId());
            }
            if (!other.getName().isEmpty()) {
                this.name_ = other.name_;
                onChanged();
            }
            if (!other.getDescription().isEmpty()) {
                this.description_ = other.description_;
                onChanged();
            }
            if (other.getMin() != 0.0d) {
                setMin(other.getMin());
            }
            if (other.getMax() != 0.0d) {
                setMax(other.getMax());
            }
            if (other.getDefault() != 0.0d) {
                setDefault(other.getDefault());
            }
            if (other.getStepSize() != 0.0d) {
                setStepSize(other.getStepSize());
            }
            if (other.type_ != 0) {
                setTypeValue(other.getTypeValue());
            }
            if (this.enumValuesBuilder_ == null) {
                if (!other.enumValues_.isEmpty()) {
                    if (this.enumValues_.isEmpty()) {
                        this.enumValues_ = other.enumValues_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureEnumValuesIsMutable();
                        this.enumValues_.addAll(other.enumValues_);
                    }
                    onChanged();
                }
            } else if (!other.enumValues_.isEmpty()) {
                if (this.enumValuesBuilder_.isEmpty()) {
                    this.enumValuesBuilder_.dispose();
                    RepeatedFieldBuilderV3<ParamEnumValue, ParamEnumValue.Builder, ParamEnumValueOrBuilder> repeatedFieldBuilderV3 = null;
                    this.enumValuesBuilder_ = null;
                    this.enumValues_ = other.enumValues_;
                    this.bitField0_ &= -2;
                    if (ParamInfo.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getEnumValuesFieldBuilder();
                    }
                    this.enumValuesBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.enumValuesBuilder_.addAllMessages(other.enumValues_);
                }
            }
            if (!other.getUnit().isEmpty()) {
                this.unit_ = other.unit_;
                onChanged();
            }
            if (!other.getFormatting().isEmpty()) {
                this.formatting_ = other.formatting_;
                onChanged();
            }
            m398mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m395mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            ParamInfo parsedMessage = null;
            try {
                try {
                    parsedMessage = (ParamInfo) ParamInfo.PARSER.parsePartialFrom(input, extensionRegistry);
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

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
        public boolean hasId() {
            return (this.idBuilder_ == null && this.id_ == null) ? false : true;
        }

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
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

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
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

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
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

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
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
            this.name_ = ParamInfo.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString value) {
            if (value != null) {
                ParamInfo.checkByteStringIsUtf8(value);
                this.name_ = value;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
        public String getDescription() {
            Object ref = this.description_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString) ref;
                String s = bs.toStringUtf8();
                this.description_ = s;
                return s;
            }
            return (String) ref;
        }

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
        public ByteString getDescriptionBytes() {
            Object ref = this.description_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.description_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        public Builder setDescription(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.description_ = value;
            onChanged();
            return this;
        }

        public Builder clearDescription() {
            this.description_ = ParamInfo.getDefaultInstance().getDescription();
            onChanged();
            return this;
        }

        public Builder setDescriptionBytes(ByteString value) {
            if (value != null) {
                ParamInfo.checkByteStringIsUtf8(value);
                this.description_ = value;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
        public double getMin() {
            return this.min_;
        }

        public Builder setMin(double value) {
            this.min_ = value;
            onChanged();
            return this;
        }

        public Builder clearMin() {
            this.min_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
        public double getMax() {
            return this.max_;
        }

        public Builder setMax(double value) {
            this.max_ = value;
            onChanged();
            return this;
        }

        public Builder clearMax() {
            this.max_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
        public double getDefault() {
            return this.default_;
        }

        public Builder setDefault(double value) {
            this.default_ = value;
            onChanged();
            return this;
        }

        public Builder clearDefault() {
            this.default_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
        public double getStepSize() {
            return this.stepSize_;
        }

        public Builder setStepSize(double value) {
            this.stepSize_ = value;
            onChanged();
            return this;
        }

        public Builder clearStepSize() {
            this.stepSize_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
        public int getTypeValue() {
            return this.type_;
        }

        public Builder setTypeValue(int value) {
            this.type_ = value;
            onChanged();
            return this;
        }

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
        public ParamType getType() {
            ParamType result = ParamType.valueOf(this.type_);
            return result == null ? ParamType.UNRECOGNIZED : result;
        }

        public Builder setType(ParamType value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.type_ = value.getNumber();
            onChanged();
            return this;
        }

        public Builder clearType() {
            this.type_ = 0;
            onChanged();
            return this;
        }

        private void ensureEnumValuesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.enumValues_ = new ArrayList(this.enumValues_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
        public List<ParamEnumValue> getEnumValuesList() {
            RepeatedFieldBuilderV3<ParamEnumValue, ParamEnumValue.Builder, ParamEnumValueOrBuilder> repeatedFieldBuilderV3 = this.enumValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.enumValues_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
        public int getEnumValuesCount() {
            RepeatedFieldBuilderV3<ParamEnumValue, ParamEnumValue.Builder, ParamEnumValueOrBuilder> repeatedFieldBuilderV3 = this.enumValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.enumValues_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
        public ParamEnumValue getEnumValues(int index) {
            RepeatedFieldBuilderV3<ParamEnumValue, ParamEnumValue.Builder, ParamEnumValueOrBuilder> repeatedFieldBuilderV3 = this.enumValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.enumValues_.get(index);
            }
            return repeatedFieldBuilderV3.getMessage(index);
        }

        public Builder setEnumValues(int index, ParamEnumValue value) {
            RepeatedFieldBuilderV3<ParamEnumValue, ParamEnumValue.Builder, ParamEnumValueOrBuilder> repeatedFieldBuilderV3 = this.enumValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureEnumValuesIsMutable();
                this.enumValues_.set(index, value);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(index, value);
            }
            return this;
        }

        public Builder setEnumValues(int index, ParamEnumValue.Builder builderForValue) {
            RepeatedFieldBuilderV3<ParamEnumValue, ParamEnumValue.Builder, ParamEnumValueOrBuilder> repeatedFieldBuilderV3 = this.enumValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEnumValuesIsMutable();
                this.enumValues_.set(index, builderForValue.m306build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(index, builderForValue.m306build());
            }
            return this;
        }

        public Builder addEnumValues(ParamEnumValue value) {
            RepeatedFieldBuilderV3<ParamEnumValue, ParamEnumValue.Builder, ParamEnumValueOrBuilder> repeatedFieldBuilderV3 = this.enumValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureEnumValuesIsMutable();
                this.enumValues_.add(value);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(value);
            }
            return this;
        }

        public Builder addEnumValues(int index, ParamEnumValue value) {
            RepeatedFieldBuilderV3<ParamEnumValue, ParamEnumValue.Builder, ParamEnumValueOrBuilder> repeatedFieldBuilderV3 = this.enumValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureEnumValuesIsMutable();
                this.enumValues_.add(index, value);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(index, value);
            }
            return this;
        }

        public Builder addEnumValues(ParamEnumValue.Builder builderForValue) {
            RepeatedFieldBuilderV3<ParamEnumValue, ParamEnumValue.Builder, ParamEnumValueOrBuilder> repeatedFieldBuilderV3 = this.enumValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEnumValuesIsMutable();
                this.enumValues_.add(builderForValue.m306build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builderForValue.m306build());
            }
            return this;
        }

        public Builder addEnumValues(int index, ParamEnumValue.Builder builderForValue) {
            RepeatedFieldBuilderV3<ParamEnumValue, ParamEnumValue.Builder, ParamEnumValueOrBuilder> repeatedFieldBuilderV3 = this.enumValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEnumValuesIsMutable();
                this.enumValues_.add(index, builderForValue.m306build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(index, builderForValue.m306build());
            }
            return this;
        }

        public Builder addAllEnumValues(Iterable<? extends ParamEnumValue> values) {
            RepeatedFieldBuilderV3<ParamEnumValue, ParamEnumValue.Builder, ParamEnumValueOrBuilder> repeatedFieldBuilderV3 = this.enumValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEnumValuesIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.enumValues_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(values);
            }
            return this;
        }

        public Builder clearEnumValues() {
            RepeatedFieldBuilderV3<ParamEnumValue, ParamEnumValue.Builder, ParamEnumValueOrBuilder> repeatedFieldBuilderV3 = this.enumValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.enumValues_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeEnumValues(int index) {
            RepeatedFieldBuilderV3<ParamEnumValue, ParamEnumValue.Builder, ParamEnumValueOrBuilder> repeatedFieldBuilderV3 = this.enumValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEnumValuesIsMutable();
                this.enumValues_.remove(index);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(index);
            }
            return this;
        }

        public ParamEnumValue.Builder getEnumValuesBuilder(int index) {
            return getEnumValuesFieldBuilder().getBuilder(index);
        }

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
        public ParamEnumValueOrBuilder getEnumValuesOrBuilder(int index) {
            RepeatedFieldBuilderV3<ParamEnumValue, ParamEnumValue.Builder, ParamEnumValueOrBuilder> repeatedFieldBuilderV3 = this.enumValuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.enumValues_.get(index);
            }
            return (ParamEnumValueOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(index);
        }

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
        public List<? extends ParamEnumValueOrBuilder> getEnumValuesOrBuilderList() {
            RepeatedFieldBuilderV3<ParamEnumValue, ParamEnumValue.Builder, ParamEnumValueOrBuilder> repeatedFieldBuilderV3 = this.enumValuesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.enumValues_);
        }

        public ParamEnumValue.Builder addEnumValuesBuilder() {
            return getEnumValuesFieldBuilder().addBuilder(ParamEnumValue.getDefaultInstance());
        }

        public ParamEnumValue.Builder addEnumValuesBuilder(int index) {
            return getEnumValuesFieldBuilder().addBuilder(index, ParamEnumValue.getDefaultInstance());
        }

        public List<ParamEnumValue.Builder> getEnumValuesBuilderList() {
            return getEnumValuesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<ParamEnumValue, ParamEnumValue.Builder, ParamEnumValueOrBuilder> getEnumValuesFieldBuilder() {
            if (this.enumValuesBuilder_ == null) {
                this.enumValuesBuilder_ = new RepeatedFieldBuilderV3<>(this.enumValues_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.enumValues_ = null;
            }
            return this.enumValuesBuilder_;
        }

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
        public String getUnit() {
            Object ref = this.unit_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString) ref;
                String s = bs.toStringUtf8();
                this.unit_ = s;
                return s;
            }
            return (String) ref;
        }

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
        public ByteString getUnitBytes() {
            Object ref = this.unit_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.unit_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        public Builder setUnit(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.unit_ = value;
            onChanged();
            return this;
        }

        public Builder clearUnit() {
            this.unit_ = ParamInfo.getDefaultInstance().getUnit();
            onChanged();
            return this;
        }

        public Builder setUnitBytes(ByteString value) {
            if (value != null) {
                ParamInfo.checkByteStringIsUtf8(value);
                this.unit_ = value;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
        public String getFormatting() {
            Object ref = this.formatting_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString) ref;
                String s = bs.toStringUtf8();
                this.formatting_ = s;
                return s;
            }
            return (String) ref;
        }

        @Override // com.dirac.acs.datastore.ParamInfoOrBuilder
        public ByteString getFormattingBytes() {
            Object ref = this.formatting_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.formatting_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        public Builder setFormatting(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.formatting_ = value;
            onChanged();
            return this;
        }

        public Builder clearFormatting() {
            this.formatting_ = ParamInfo.getDefaultInstance().getFormatting();
            onChanged();
            return this;
        }

        public Builder setFormattingBytes(ByteString value) {
            if (value != null) {
                ParamInfo.checkByteStringIsUtf8(value);
                this.formatting_ = value;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m404setUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.setUnknownFields(unknownFields);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m398mergeUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.mergeUnknownFields(unknownFields);
        }
    }

    public static ParamInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ParamInfo> parser() {
        return PARSER;
    }

    public Parser<ParamInfo> getParserForType() {
        return PARSER;
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ParamInfo m360getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
