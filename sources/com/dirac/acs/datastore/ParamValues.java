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
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class ParamValues extends GeneratedMessageV3 implements ParamValuesOrBuilder {
    private static final ParamValues DEFAULT_INSTANCE = new ParamValues();
    private static final Parser<ParamValues> PARSER = new AbstractParser<ParamValues>() { // from class: com.dirac.acs.datastore.ParamValues.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ParamValues m516parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new ParamValues(input, extensionRegistry);
        }
    };
    public static final int VALUES_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private List<ParamValue> values_;

    private ParamValues(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ParamValues() {
        this.memoizedIsInitialized = (byte) -1;
        this.values_ = Collections.emptyList();
    }

    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
        return new ParamValues();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0011. Please report as an issue. */
    private ParamValues(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                                if ((mutable_bitField0_ & 1) == 0) {
                                    this.values_ = new ArrayList();
                                    mutable_bitField0_ |= 1;
                                }
                                this.values_.add((ParamValue) input.readMessage(ParamValue.parser(), extensionRegistry));
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
                if ((mutable_bitField0_ & 1) != 0) {
                    this.values_ = Collections.unmodifiableList(this.values_);
                }
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamValues_descriptor;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamValues_fieldAccessorTable.ensureFieldAccessorsInitialized(ParamValues.class, Builder.class);
    }

    @Override // com.dirac.acs.datastore.ParamValuesOrBuilder
    public List<ParamValue> getValuesList() {
        return this.values_;
    }

    @Override // com.dirac.acs.datastore.ParamValuesOrBuilder
    public List<? extends ParamValueOrBuilder> getValuesOrBuilderList() {
        return this.values_;
    }

    @Override // com.dirac.acs.datastore.ParamValuesOrBuilder
    public int getValuesCount() {
        return this.values_.size();
    }

    @Override // com.dirac.acs.datastore.ParamValuesOrBuilder
    public ParamValue getValues(int index) {
        return this.values_.get(index);
    }

    @Override // com.dirac.acs.datastore.ParamValuesOrBuilder
    public ParamValueOrBuilder getValuesOrBuilder(int index) {
        return this.values_.get(index);
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
        for (int i = 0; i < this.values_.size(); i++) {
            output.writeMessage(1, this.values_.get(i));
        }
        this.unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
        int size = this.memoizedSize;
        if (size != -1) {
            return size;
        }
        int size2 = 0;
        for (int i = 0; i < this.values_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.values_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = size3;
        return size3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ParamValues)) {
            return super.equals(obj);
        }
        ParamValues other = (ParamValues) obj;
        return getValuesList().equals(other.getValuesList()) && this.unknownFields.equals(other.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = (41 * 19) + getDescriptor().hashCode();
        if (getValuesCount() > 0) {
            hash = (((hash * 37) + 1) * 53) + getValuesList().hashCode();
        }
        int hash2 = (hash * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hash2;
        return hash2;
    }

    public static ParamValues parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (ParamValues) PARSER.parseFrom(data);
    }

    public static ParamValues parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ParamValues) PARSER.parseFrom(data, extensionRegistry);
    }

    public static ParamValues parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ParamValues) PARSER.parseFrom(data);
    }

    public static ParamValues parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ParamValues) PARSER.parseFrom(data, extensionRegistry);
    }

    public static ParamValues parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ParamValues) PARSER.parseFrom(data);
    }

    public static ParamValues parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ParamValues) PARSER.parseFrom(data, extensionRegistry);
    }

    public static ParamValues parseFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static ParamValues parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static ParamValues parseDelimitedFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static ParamValues parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static ParamValues parseFrom(CodedInputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static ParamValues parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m513newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m515toBuilder();
    }

    public static Builder newBuilder(ParamValues prototype) {
        return DEFAULT_INSTANCE.m515toBuilder().mergeFrom(prototype);
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m515toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m512newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ParamValuesOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> valuesBuilder_;
        private List<ParamValue> values_;

        public static final Descriptors.Descriptor getDescriptor() {
            return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamValues_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamValues_fieldAccessorTable.ensureFieldAccessorsInitialized(ParamValues.class, Builder.class);
        }

        private Builder() {
            this.values_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            this.values_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (ParamValues.alwaysUseFieldBuilders) {
                getValuesFieldBuilder();
            }
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m526clear() {
            super.clear();
            RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.values_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamValues_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ParamValues m539getDefaultInstanceForType() {
            return ParamValues.getDefaultInstance();
        }

        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ParamValues m520build() {
            ParamValues result = m522buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ParamValues m522buildPartial() {
            ParamValues result = new ParamValues(this);
            int i = this.bitField0_;
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
            onBuilt();
            return result;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m537clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m550setField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.setField(field, value);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m528clearField(Descriptors.FieldDescriptor field) {
            return (Builder) super.clearField(field);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m531clearOneof(Descriptors.OneofDescriptor oneof) {
            return (Builder) super.clearOneof(oneof);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m552setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
            return (Builder) super.setRepeatedField(field, index, value);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m518addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.addRepeatedField(field, value);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m544mergeFrom(Message other) {
            if (other instanceof ParamValues) {
                return mergeFrom((ParamValues) other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(ParamValues other) {
            if (other == ParamValues.getDefaultInstance()) {
                return this;
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
                    if (ParamValues.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getValuesFieldBuilder();
                    }
                    this.valuesBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.valuesBuilder_.addAllMessages(other.values_);
                }
            }
            m548mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m545mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            ParamValues parsedMessage = null;
            try {
                try {
                    parsedMessage = (ParamValues) ParamValues.PARSER.parsePartialFrom(input, extensionRegistry);
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

        private void ensureValuesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.values_ = new ArrayList(this.values_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.dirac.acs.datastore.ParamValuesOrBuilder
        public List<ParamValue> getValuesList() {
            RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.values_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.dirac.acs.datastore.ParamValuesOrBuilder
        public int getValuesCount() {
            RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.values_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.dirac.acs.datastore.ParamValuesOrBuilder
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

        @Override // com.dirac.acs.datastore.ParamValuesOrBuilder
        public ParamValueOrBuilder getValuesOrBuilder(int index) {
            RepeatedFieldBuilderV3<ParamValue, ParamValue.Builder, ParamValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.values_.get(index);
            }
            return (ParamValueOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(index);
        }

        @Override // com.dirac.acs.datastore.ParamValuesOrBuilder
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

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m554setUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.setUnknownFields(unknownFields);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m548mergeUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.mergeUnknownFields(unknownFields);
        }
    }

    public static ParamValues getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ParamValues> parser() {
        return PARSER;
    }

    public Parser<ParamValues> getParserForType() {
        return PARSER;
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ParamValues m510getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
