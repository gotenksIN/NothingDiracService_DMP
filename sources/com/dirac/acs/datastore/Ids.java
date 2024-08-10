package com.dirac.acs.datastore;

import com.dirac.acs.datastore.Id;
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
public final class Ids extends GeneratedMessageV3 implements IdsOrBuilder {
    public static final int IDS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private List<Id> ids_;
    private byte memoizedIsInitialized;
    private static final Ids DEFAULT_INSTANCE = new Ids();
    private static final Parser<Ids> PARSER = new AbstractParser<Ids>() { // from class: com.dirac.acs.datastore.Ids.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Ids m252parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Ids(input, extensionRegistry);
        }
    };

    private Ids(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Ids() {
        this.memoizedIsInitialized = (byte) -1;
        this.ids_ = Collections.emptyList();
    }

    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
        return new Ids();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0011. Please report as an issue. */
    private Ids(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                                    this.ids_ = new ArrayList();
                                    mutable_bitField0_ |= 1;
                                }
                                this.ids_.add((Id) input.readMessage(Id.parser(), extensionRegistry));
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
                    this.ids_ = Collections.unmodifiableList(this.ids_);
                }
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ParameterMessages.internal_static_com_dirac_acs_datastore_Ids_descriptor;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ParameterMessages.internal_static_com_dirac_acs_datastore_Ids_fieldAccessorTable.ensureFieldAccessorsInitialized(Ids.class, Builder.class);
    }

    @Override // com.dirac.acs.datastore.IdsOrBuilder
    public List<Id> getIdsList() {
        return this.ids_;
    }

    @Override // com.dirac.acs.datastore.IdsOrBuilder
    public List<? extends IdOrBuilder> getIdsOrBuilderList() {
        return this.ids_;
    }

    @Override // com.dirac.acs.datastore.IdsOrBuilder
    public int getIdsCount() {
        return this.ids_.size();
    }

    @Override // com.dirac.acs.datastore.IdsOrBuilder
    public Id getIds(int index) {
        return this.ids_.get(index);
    }

    @Override // com.dirac.acs.datastore.IdsOrBuilder
    public IdOrBuilder getIdsOrBuilder(int index) {
        return this.ids_.get(index);
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
        for (int i = 0; i < this.ids_.size(); i++) {
            output.writeMessage(1, this.ids_.get(i));
        }
        this.unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
        int size = this.memoizedSize;
        if (size != -1) {
            return size;
        }
        int size2 = 0;
        for (int i = 0; i < this.ids_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.ids_.get(i));
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = size3;
        return size3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Ids)) {
            return super.equals(obj);
        }
        Ids other = (Ids) obj;
        return getIdsList().equals(other.getIdsList()) && this.unknownFields.equals(other.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = (41 * 19) + getDescriptor().hashCode();
        if (getIdsCount() > 0) {
            hash = (((hash * 37) + 1) * 53) + getIdsList().hashCode();
        }
        int hash2 = (hash * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hash2;
        return hash2;
    }

    public static Ids parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Ids) PARSER.parseFrom(data);
    }

    public static Ids parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Ids) PARSER.parseFrom(data, extensionRegistry);
    }

    public static Ids parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Ids) PARSER.parseFrom(data);
    }

    public static Ids parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Ids) PARSER.parseFrom(data, extensionRegistry);
    }

    public static Ids parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Ids) PARSER.parseFrom(data);
    }

    public static Ids parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Ids) PARSER.parseFrom(data, extensionRegistry);
    }

    public static Ids parseFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static Ids parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static Ids parseDelimitedFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static Ids parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static Ids parseFrom(CodedInputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static Ids parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m249newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m251toBuilder();
    }

    public static Builder newBuilder(Ids prototype) {
        return DEFAULT_INSTANCE.m251toBuilder().mergeFrom(prototype);
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m251toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m248newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements IdsOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Id, Id.Builder, IdOrBuilder> idsBuilder_;
        private List<Id> ids_;

        public static final Descriptors.Descriptor getDescriptor() {
            return ParameterMessages.internal_static_com_dirac_acs_datastore_Ids_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ParameterMessages.internal_static_com_dirac_acs_datastore_Ids_fieldAccessorTable.ensureFieldAccessorsInitialized(Ids.class, Builder.class);
        }

        private Builder() {
            this.ids_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            this.ids_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Ids.alwaysUseFieldBuilders) {
                getIdsFieldBuilder();
            }
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m262clear() {
            super.clear();
            RepeatedFieldBuilderV3<Id, Id.Builder, IdOrBuilder> repeatedFieldBuilderV3 = this.idsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.ids_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ParameterMessages.internal_static_com_dirac_acs_datastore_Ids_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Ids m275getDefaultInstanceForType() {
            return Ids.getDefaultInstance();
        }

        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Ids m256build() {
            Ids result = m258buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Ids m258buildPartial() {
            Ids result = new Ids(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Id, Id.Builder, IdOrBuilder> repeatedFieldBuilderV3 = this.idsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 1) != 0) {
                    this.ids_ = Collections.unmodifiableList(this.ids_);
                    this.bitField0_ &= -2;
                }
                result.ids_ = this.ids_;
            } else {
                result.ids_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return result;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m273clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m286setField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.setField(field, value);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m264clearField(Descriptors.FieldDescriptor field) {
            return (Builder) super.clearField(field);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m267clearOneof(Descriptors.OneofDescriptor oneof) {
            return (Builder) super.clearOneof(oneof);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m288setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
            return (Builder) super.setRepeatedField(field, index, value);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m254addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.addRepeatedField(field, value);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m280mergeFrom(Message other) {
            if (other instanceof Ids) {
                return mergeFrom((Ids) other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Ids other) {
            if (other == Ids.getDefaultInstance()) {
                return this;
            }
            if (this.idsBuilder_ == null) {
                if (!other.ids_.isEmpty()) {
                    if (this.ids_.isEmpty()) {
                        this.ids_ = other.ids_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureIdsIsMutable();
                        this.ids_.addAll(other.ids_);
                    }
                    onChanged();
                }
            } else if (!other.ids_.isEmpty()) {
                if (this.idsBuilder_.isEmpty()) {
                    this.idsBuilder_.dispose();
                    RepeatedFieldBuilderV3<Id, Id.Builder, IdOrBuilder> repeatedFieldBuilderV3 = null;
                    this.idsBuilder_ = null;
                    this.ids_ = other.ids_;
                    this.bitField0_ &= -2;
                    if (Ids.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getIdsFieldBuilder();
                    }
                    this.idsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.idsBuilder_.addAllMessages(other.ids_);
                }
            }
            m284mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m281mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Ids parsedMessage = null;
            try {
                try {
                    parsedMessage = (Ids) Ids.PARSER.parsePartialFrom(input, extensionRegistry);
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

        private void ensureIdsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.ids_ = new ArrayList(this.ids_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.dirac.acs.datastore.IdsOrBuilder
        public List<Id> getIdsList() {
            RepeatedFieldBuilderV3<Id, Id.Builder, IdOrBuilder> repeatedFieldBuilderV3 = this.idsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.ids_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.dirac.acs.datastore.IdsOrBuilder
        public int getIdsCount() {
            RepeatedFieldBuilderV3<Id, Id.Builder, IdOrBuilder> repeatedFieldBuilderV3 = this.idsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.ids_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.dirac.acs.datastore.IdsOrBuilder
        public Id getIds(int index) {
            RepeatedFieldBuilderV3<Id, Id.Builder, IdOrBuilder> repeatedFieldBuilderV3 = this.idsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.ids_.get(index);
            }
            return repeatedFieldBuilderV3.getMessage(index);
        }

        public Builder setIds(int index, Id value) {
            RepeatedFieldBuilderV3<Id, Id.Builder, IdOrBuilder> repeatedFieldBuilderV3 = this.idsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureIdsIsMutable();
                this.ids_.set(index, value);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(index, value);
            }
            return this;
        }

        public Builder setIds(int index, Id.Builder builderForValue) {
            RepeatedFieldBuilderV3<Id, Id.Builder, IdOrBuilder> repeatedFieldBuilderV3 = this.idsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureIdsIsMutable();
                this.ids_.set(index, builderForValue.m207build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(index, builderForValue.m207build());
            }
            return this;
        }

        public Builder addIds(Id value) {
            RepeatedFieldBuilderV3<Id, Id.Builder, IdOrBuilder> repeatedFieldBuilderV3 = this.idsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureIdsIsMutable();
                this.ids_.add(value);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(value);
            }
            return this;
        }

        public Builder addIds(int index, Id value) {
            RepeatedFieldBuilderV3<Id, Id.Builder, IdOrBuilder> repeatedFieldBuilderV3 = this.idsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureIdsIsMutable();
                this.ids_.add(index, value);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(index, value);
            }
            return this;
        }

        public Builder addIds(Id.Builder builderForValue) {
            RepeatedFieldBuilderV3<Id, Id.Builder, IdOrBuilder> repeatedFieldBuilderV3 = this.idsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureIdsIsMutable();
                this.ids_.add(builderForValue.m207build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builderForValue.m207build());
            }
            return this;
        }

        public Builder addIds(int index, Id.Builder builderForValue) {
            RepeatedFieldBuilderV3<Id, Id.Builder, IdOrBuilder> repeatedFieldBuilderV3 = this.idsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureIdsIsMutable();
                this.ids_.add(index, builderForValue.m207build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(index, builderForValue.m207build());
            }
            return this;
        }

        public Builder addAllIds(Iterable<? extends Id> values) {
            RepeatedFieldBuilderV3<Id, Id.Builder, IdOrBuilder> repeatedFieldBuilderV3 = this.idsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureIdsIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.ids_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(values);
            }
            return this;
        }

        public Builder clearIds() {
            RepeatedFieldBuilderV3<Id, Id.Builder, IdOrBuilder> repeatedFieldBuilderV3 = this.idsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.ids_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeIds(int index) {
            RepeatedFieldBuilderV3<Id, Id.Builder, IdOrBuilder> repeatedFieldBuilderV3 = this.idsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureIdsIsMutable();
                this.ids_.remove(index);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(index);
            }
            return this;
        }

        public Id.Builder getIdsBuilder(int index) {
            return getIdsFieldBuilder().getBuilder(index);
        }

        @Override // com.dirac.acs.datastore.IdsOrBuilder
        public IdOrBuilder getIdsOrBuilder(int index) {
            RepeatedFieldBuilderV3<Id, Id.Builder, IdOrBuilder> repeatedFieldBuilderV3 = this.idsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.ids_.get(index);
            }
            return (IdOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(index);
        }

        @Override // com.dirac.acs.datastore.IdsOrBuilder
        public List<? extends IdOrBuilder> getIdsOrBuilderList() {
            RepeatedFieldBuilderV3<Id, Id.Builder, IdOrBuilder> repeatedFieldBuilderV3 = this.idsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.ids_);
        }

        public Id.Builder addIdsBuilder() {
            return getIdsFieldBuilder().addBuilder(Id.getDefaultInstance());
        }

        public Id.Builder addIdsBuilder(int index) {
            return getIdsFieldBuilder().addBuilder(index, Id.getDefaultInstance());
        }

        public List<Id.Builder> getIdsBuilderList() {
            return getIdsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Id, Id.Builder, IdOrBuilder> getIdsFieldBuilder() {
            if (this.idsBuilder_ == null) {
                this.idsBuilder_ = new RepeatedFieldBuilderV3<>(this.ids_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.ids_ = null;
            }
            return this.idsBuilder_;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m290setUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.setUnknownFields(unknownFields);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m284mergeUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.mergeUnknownFields(unknownFields);
        }
    }

    public static Ids getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Ids> parser() {
        return PARSER;
    }

    public Parser<Ids> getParserForType() {
        return PARSER;
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Ids m246getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
