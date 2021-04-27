<template>
  <div class="progress">{{ progressString }}</div>
</template>

<script lang="ts">
import { Todo } from "@/utils/declarations";
import { Component, Prop, Vue } from "vue-property-decorator";

@Component
export default class TodoProgress extends Vue {
  @Prop() todo!: Todo;

  get progressString() {
    if (this.todo?.tasks?.filter) {
      const countCompleted = this.todo.tasks.filter((t) => t.completed).length;
      const isDone = countCompleted === this.todo.tasks.length;
      const progressIndicator = isDone ? "✅ " : "";
      return `${progressIndicator}(${countCompleted} / ${this.todo.tasks.length})`;
    }
    return "";
  }
}
</script>

<style scoped lang="scss">
div.progress {
  line-height: 1.4em;
}
</style>
