<template>
  <div class="inputsection">
      <input class="title" type="text" v-model="item.title" placeholder="Title" @keypress="$emit('update')" />
      <textarea class="description" v-model="item.description" placeholder="Description" @keypress="$emit('update')"></textarea>
  </div>
</template>

<script lang="ts">
import { Todo } from '@/utils/declarations';
import { Component, Prop, Vue } from 'vue-property-decorator';

@Component
export default class InputSection extends Vue {
    @Prop() item!: Todo;
}
</script>

<style scoped lang="scss">
@import '../scss/variables';
    .inputsection {
        display: flex;
        flex-direction: column;
        flex:1;
        padding: $xl 0 0 $xxl;
    }
    .title {
        height: 64px;
        border: none;
        font-size: 36px;
    }
    .description {
        flex: 1;
        border: none;
        padding: $l $xxl ($xxl * 2)  0 ;
    }
    .title:focus,
    .description:focus {
        outline: none;
    }
</style>
