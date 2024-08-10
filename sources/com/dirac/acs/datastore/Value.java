package com.dirac.acs.datastore;

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
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class Value extends GeneratedMessageV3 implements ValueOrBuilder {
    public static final int BINARY_VALUE_FIELD_NUMBER = 6;
    public static final int BOOL_VALUE_FIELD_NUMBER = 5;
    public static final int FLOAT_VALUE_FIELD_NUMBER = 2;
    public static final int INT32_VALUE_FIELD_NUMBER = 3;
    public static final int INT64_VALUE_FIELD_NUMBER = 4;
    public static final int STRING_VALUE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private int valueCase_;
    private Object value_;
    private static final Value DEFAULT_INSTANCE = new Value();
    private static final Parser<Value> PARSER = new AbstractParser<Value>() { // from class: com.dirac.acs.datastore.Value.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Value m726parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Value(input, extensionRegistry);
        }
    };

    private Value(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.valueCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private Value() {
        this.valueCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
        return new Value();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0010. Please report as an issue. */
    private Value(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                            String s = input.readStringRequireUtf8();
                            this.valueCase_ = 1;
                            this.value_ = s;
                        case 21:
                            this.valueCase_ = 2;
                            this.value_ = Float.valueOf(input.readFloat());
                        case 24:
                            this.valueCase_ = 3;
                            this.value_ = Integer.valueOf(input.readInt32());
                        case 32:
                            this.valueCase_ = 4;
                            this.value_ = Long.valueOf(input.readInt64());
                        case 40:
                            this.valueCase_ = 5;
                            this.value_ = Boolean.valueOf(input.readBool());
                        case 50:
                            this.valueCase_ = 6;
                            this.value_ = input.readBytes();
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
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return DarInterface.internal_static_com_dirac_acs_datastore_Value_descriptor;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DarInterface.internal_static_com_dirac_acs_datastore_Value_fieldAccessorTable.ensureFieldAccessorsInitialized(Value.class, Builder.class);
    }

    /* loaded from: classes4.dex */
    public enum ValueCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        STRING_VALUE(1),
        FLOAT_VALUE(2),
        INT32_VALUE(3),
        INT64_VALUE(4),
        BOOL_VALUE(5),
        BINARY_VALUE(6),
        VALUE_NOT_SET(0);

        private final int value;

        ValueCase(int value) {
            this.value = value;
        }

        @Deprecated
        public static ValueCase valueOf(int value) {
            return forNumber(value);
        }

        public static ValueCase forNumber(int value) {
            switch (value) {
                case 0:
                    return VALUE_NOT_SET;
                case 1:
                    return STRING_VALUE;
                case 2:
                    return FLOAT_VALUE;
                case 3:
                    return INT32_VALUE;
                case 4:
                    return INT64_VALUE;
                case 5:
                    return BOOL_VALUE;
                case 6:
                    return BINARY_VALUE;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.dirac.acs.datastore.ValueOrBuilder
    public ValueCase getValueCase() {
        return ValueCase.forNumber(this.valueCase_);
    }

    @Override // com.dirac.acs.datastore.ValueOrBuilder
    public String getStringValue() {
        Object ref = "";
        if (this.valueCase_ == 1) {
            ref = this.value_;
        }
        if (ref instanceof String) {
            return (String) ref;
        }
        ByteString bs = (ByteString) ref;
        String s = bs.toStringUtf8();
        if (this.valueCase_ == 1) {
            this.value_ = s;
        }
        return s;
    }

    @Override // com.dirac.acs.datastore.ValueOrBuilder
    public ByteString getStringValueBytes() {
        Object ref = "";
        if (this.valueCase_ == 1) {
            ref = this.value_;
        }
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String) ref);
            if (this.valueCase_ == 1) {
                this.value_ = b;
            }
            return b;
        }
        return (ByteString) ref;
    }

    @Override // com.dirac.acs.datastore.ValueOrBuilder
    public float getFloatValue() {
        if (this.valueCase_ == 2) {
            return ((Float) this.value_).floatValue();
        }
        return 0.0f;
    }

    @Override // com.dirac.acs.datastore.ValueOrBuilder
    public int getInt32Value() {
        if (this.valueCase_ == 3) {
            return ((Integer) this.value_).intValue();
        }
        return 0;
    }

    @Override // com.dirac.acs.datastore.ValueOrBuilder
    public long getInt64Value() {
        if (this.valueCase_ == 4) {
            return ((Long) this.value_).longValue();
        }
        return 0L;
    }

    @Override // com.dirac.acs.datastore.ValueOrBuilder
    public boolean getBoolValue() {
        if (this.valueCase_ == 5) {
            return ((Boolean) this.value_).booleanValue();
        }
        return false;
    }

    @Override // com.dirac.acs.datastore.ValueOrBuilder
    public ByteString getBinaryValue() {
        if (this.valueCase_ == 6) {
            return (ByteString) this.value_;
        }
        return ByteString.EMPTY;
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
        if (this.valueCase_ == 1) {
            GeneratedMessageV3.writeString(output, 1, this.value_);
        }
        if (this.valueCase_ == 2) {
            output.writeFloat(2, ((Float) this.value_).floatValue());
        }
        if (this.valueCase_ == 3) {
            output.writeInt32(3, ((Integer) this.value_).intValue());
        }
        if (this.valueCase_ == 4) {
            output.writeInt64(4, ((Long) this.value_).longValue());
        }
        if (this.valueCase_ == 5) {
            output.writeBool(5, ((Boolean) this.value_).booleanValue());
        }
        if (this.valueCase_ == 6) {
            output.writeBytes(6, (ByteString) this.value_);
        }
        this.unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
        int size = this.memoizedSize;
        if (size != -1) {
            return size;
        }
        int size2 = this.valueCase_ == 1 ? 0 + GeneratedMessageV3.computeStringSize(1, this.value_) : 0;
        if (this.valueCase_ == 2) {
            size2 += CodedOutputStream.computeFloatSize(2, ((Float) this.value_).floatValue());
        }
        if (this.valueCase_ == 3) {
            size2 += CodedOutputStream.computeInt32Size(3, ((Integer) this.value_).intValue());
        }
        if (this.valueCase_ == 4) {
            size2 += CodedOutputStream.computeInt64Size(4, ((Long) this.value_).longValue());
        }
        if (this.valueCase_ == 5) {
            size2 += CodedOutputStream.computeBoolSize(5, ((Boolean) this.value_).booleanValue());
        }
        if (this.valueCase_ == 6) {
            size2 += CodedOutputStream.computeBytesSize(6, (ByteString) this.value_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = size3;
        return size3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Value)) {
            return super.equals(obj);
        }
        Value other = (Value) obj;
        if (!getValueCase().equals(other.getValueCase())) {
            return false;
        }
        switch (this.valueCase_) {
            case 1:
                if (!getStringValue().equals(other.getStringValue())) {
                    return false;
                }
                break;
            case 2:
                if (Float.floatToIntBits(getFloatValue()) != Float.floatToIntBits(other.getFloatValue())) {
                    return false;
                }
                break;
            case 3:
                if (getInt32Value() != other.getInt32Value()) {
                    return false;
                }
                break;
            case 4:
                if (getInt64Value() != other.getInt64Value()) {
                    return false;
                }
                break;
            case 5:
                if (getBoolValue() != other.getBoolValue()) {
                    return false;
                }
                break;
            case 6:
                if (!getBinaryValue().equals(other.getBinaryValue())) {
                    return false;
                }
                break;
        }
        return this.unknownFields.equals(other.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = (41 * 19) + getDescriptor().hashCode();
        switch (this.valueCase_) {
            case 1:
                hash = (((hash * 37) + 1) * 53) + getStringValue().hashCode();
                break;
            case 2:
                hash = (((hash * 37) + 2) * 53) + Float.floatToIntBits(getFloatValue());
                break;
            case 3:
                hash = (((hash * 37) + 3) * 53) + getInt32Value();
                break;
            case 4:
                hash = (((hash * 37) + 4) * 53) + Internal.hashLong(getInt64Value());
                break;
            case 5:
                hash = (((hash * 37) + 5) * 53) + Internal.hashBoolean(getBoolValue());
                break;
            case 6:
                hash = (((hash * 37) + 6) * 53) + getBinaryValue().hashCode();
                break;
        }
        int hash2 = (hash * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hash2;
        return hash2;
    }

    public static Value parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Value) PARSER.parseFrom(data);
    }

    public static Value parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Value) PARSER.parseFrom(data, extensionRegistry);
    }

    public static Value parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Value) PARSER.parseFrom(data);
    }

    public static Value parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Value) PARSER.parseFrom(data, extensionRegistry);
    }

    public static Value parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Value) PARSER.parseFrom(data);
    }

    public static Value parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Value) PARSER.parseFrom(data, extensionRegistry);
    }

    public static Value parseFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static Value parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static Value parseDelimitedFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static Value parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static Value parseFrom(CodedInputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static Value parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m723newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m725toBuilder();
    }

    public static Builder newBuilder(Value prototype) {
        return DEFAULT_INSTANCE.m725toBuilder().mergeFrom(prototype);
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m725toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m722newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ValueOrBuilder {
        private int valueCase_;
        private Object value_;

        public static final Descriptors.Descriptor getDescriptor() {
            return DarInterface.internal_static_com_dirac_acs_datastore_Value_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DarInterface.internal_static_com_dirac_acs_datastore_Value_fieldAccessorTable.ensureFieldAccessorsInitialized(Value.class, Builder.class);
        }

        private Builder() {
            this.valueCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            this.valueCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Value.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m736clear() {
            super.clear();
            this.valueCase_ = 0;
            this.value_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return DarInterface.internal_static_com_dirac_acs_datastore_Value_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Value m749getDefaultInstanceForType() {
            return Value.getDefaultInstance();
        }

        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Value m730build() {
            Value result = m732buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Value m732buildPartial() {
            Value result = new Value(this);
            if (this.valueCase_ == 1) {
                result.value_ = this.value_;
            }
            if (this.valueCase_ == 2) {
                result.value_ = this.value_;
            }
            if (this.valueCase_ == 3) {
                result.value_ = this.value_;
            }
            if (this.valueCase_ == 4) {
                result.value_ = this.value_;
            }
            if (this.valueCase_ == 5) {
                result.value_ = this.value_;
            }
            if (this.valueCase_ == 6) {
                result.value_ = this.value_;
            }
            result.valueCase_ = this.valueCase_;
            onBuilt();
            return result;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m747clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m760setField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.setField(field, value);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m738clearField(Descriptors.FieldDescriptor field) {
            return (Builder) super.clearField(field);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m741clearOneof(Descriptors.OneofDescriptor oneof) {
            return (Builder) super.clearOneof(oneof);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m762setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
            return (Builder) super.setRepeatedField(field, index, value);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m728addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.addRepeatedField(field, value);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m754mergeFrom(Message other) {
            if (other instanceof Value) {
                return mergeFrom((Value) other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Value other) {
            if (other == Value.getDefaultInstance()) {
                return this;
            }
            switch (AnonymousClass2.$SwitchMap$com$dirac$acs$datastore$Value$ValueCase[other.getValueCase().ordinal()]) {
                case 1:
                    this.valueCase_ = 1;
                    this.value_ = other.value_;
                    onChanged();
                    break;
                case 2:
                    setFloatValue(other.getFloatValue());
                    break;
                case 3:
                    setInt32Value(other.getInt32Value());
                    break;
                case 4:
                    setInt64Value(other.getInt64Value());
                    break;
                case 5:
                    setBoolValue(other.getBoolValue());
                    break;
                case 6:
                    setBinaryValue(other.getBinaryValue());
                    break;
            }
            m758mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m755mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Value parsedMessage = null;
            try {
                try {
                    parsedMessage = (Value) Value.PARSER.parsePartialFrom(input, extensionRegistry);
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

        @Override // com.dirac.acs.datastore.ValueOrBuilder
        public ValueCase getValueCase() {
            return ValueCase.forNumber(this.valueCase_);
        }

        public Builder clearValue() {
            this.valueCase_ = 0;
            this.value_ = null;
            onChanged();
            return this;
        }

        @Override // com.dirac.acs.datastore.ValueOrBuilder
        public String getStringValue() {
            Object ref = "";
            if (this.valueCase_ == 1) {
                ref = this.value_;
            }
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString) ref;
                String s = bs.toStringUtf8();
                if (this.valueCase_ == 1) {
                    this.value_ = s;
                }
                return s;
            }
            return (String) ref;
        }

        @Override // com.dirac.acs.datastore.ValueOrBuilder
        public ByteString getStringValueBytes() {
            Object ref = "";
            if (this.valueCase_ == 1) {
                ref = this.value_;
            }
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                if (this.valueCase_ == 1) {
                    this.value_ = b;
                }
                return b;
            }
            return (ByteString) ref;
        }

        public Builder setStringValue(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.valueCase_ = 1;
            this.value_ = value;
            onChanged();
            return this;
        }

        public Builder clearStringValue() {
            if (this.valueCase_ == 1) {
                this.valueCase_ = 0;
                this.value_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setStringValueBytes(ByteString value) {
            if (value != null) {
                Value.checkByteStringIsUtf8(value);
                this.valueCase_ = 1;
                this.value_ = value;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.dirac.acs.datastore.ValueOrBuilder
        public float getFloatValue() {
            if (this.valueCase_ == 2) {
                return ((Float) this.value_).floatValue();
            }
            return 0.0f;
        }

        public Builder setFloatValue(float value) {
            this.valueCase_ = 2;
            this.value_ = Float.valueOf(value);
            onChanged();
            return this;
        }

        public Builder clearFloatValue() {
            if (this.valueCase_ == 2) {
                this.valueCase_ = 0;
                this.value_ = null;
                onChanged();
            }
            return this;
        }

        @Override // com.dirac.acs.datastore.ValueOrBuilder
        public int getInt32Value() {
            if (this.valueCase_ == 3) {
                return ((Integer) this.value_).intValue();
            }
            return 0;
        }

        public Builder setInt32Value(int value) {
            this.valueCase_ = 3;
            this.value_ = Integer.valueOf(value);
            onChanged();
            return this;
        }

        public Builder clearInt32Value() {
            if (this.valueCase_ == 3) {
                this.valueCase_ = 0;
                this.value_ = null;
                onChanged();
            }
            return this;
        }

        @Override // com.dirac.acs.datastore.ValueOrBuilder
        public long getInt64Value() {
            if (this.valueCase_ == 4) {
                return ((Long) this.value_).longValue();
            }
            return 0L;
        }

        public Builder setInt64Value(long value) {
            this.valueCase_ = 4;
            this.value_ = Long.valueOf(value);
            onChanged();
            return this;
        }

        public Builder clearInt64Value() {
            if (this.valueCase_ == 4) {
                this.valueCase_ = 0;
                this.value_ = null;
                onChanged();
            }
            return this;
        }

        @Override // com.dirac.acs.datastore.ValueOrBuilder
        public boolean getBoolValue() {
            if (this.valueCase_ == 5) {
                return ((Boolean) this.value_).booleanValue();
            }
            return false;
        }

        public Builder setBoolValue(boolean value) {
            this.valueCase_ = 5;
            this.value_ = Boolean.valueOf(value);
            onChanged();
            return this;
        }

        public Builder clearBoolValue() {
            if (this.valueCase_ == 5) {
                this.valueCase_ = 0;
                this.value_ = null;
                onChanged();
            }
            return this;
        }

        @Override // com.dirac.acs.datastore.ValueOrBuilder
        public ByteString getBinaryValue() {
            if (this.valueCase_ == 6) {
                return (ByteString) this.value_;
            }
            return ByteString.EMPTY;
        }

        public Builder setBinaryValue(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.valueCase_ = 6;
            this.value_ = value;
            onChanged();
            return this;
        }

        public Builder clearBinaryValue() {
            if (this.valueCase_ == 6) {
                this.valueCase_ = 0;
                this.value_ = null;
                onChanged();
            }
            return this;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m764setUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.setUnknownFields(unknownFields);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m758mergeUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.mergeUnknownFields(unknownFields);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.dirac.acs.datastore.Value$2, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$dirac$acs$datastore$Value$ValueCase;

        static {
            int[] iArr = new int[ValueCase.values().length];
            $SwitchMap$com$dirac$acs$datastore$Value$ValueCase = iArr;
            try {
                iArr[ValueCase.STRING_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$dirac$acs$datastore$Value$ValueCase[ValueCase.FLOAT_VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$dirac$acs$datastore$Value$ValueCase[ValueCase.INT32_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$dirac$acs$datastore$Value$ValueCase[ValueCase.INT64_VALUE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$dirac$acs$datastore$Value$ValueCase[ValueCase.BOOL_VALUE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$dirac$acs$datastore$Value$ValueCase[ValueCase.BINARY_VALUE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$dirac$acs$datastore$Value$ValueCase[ValueCase.VALUE_NOT_SET.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public static Value getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Value> parser() {
        return PARSER;
    }

    public Parser<Value> getParserForType() {
        return PARSER;
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Value m720getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
