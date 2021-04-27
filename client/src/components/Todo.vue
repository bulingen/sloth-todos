<template>
  <div class="inputsection">
    <input
      class="title"
      type="text"
      v-model="item.title"
      placeholder="Title"
      @keypress="$emit('update')"
    />
    <div>
      <form @submit.prevent="addTask(newTask)">
        <input
          type="text"
          v-model="newTask"
          placeholder="What do I need to do?"
        />
        <input type="submit" value="Save" />
      </form>
      <div class="progress-wrapper">
        <TodoProgress :todo="item" />
      </div>
      <ul class="checklist">
        <li v-for="(task, index) in item.tasks" :key="index">
          <div>
            <input
              type="checkbox"
              v-bind:id="task.id"
              v-bind:checked="task.completed"
              @change="toggleTask(task)"
            />
            <label v-bind:for="task.id">
              {{ task.name }}
            </label>
          </div>
          <button @click="removeTask(task)">🗑</button>
        </li>
      </ul>
    </div>
    <textarea
      class="description"
      v-model="item.description"
      placeholder="Description"
      @keypress="$emit('update')"
    ></textarea>
  </div>
</template>

<script lang="ts">
import { Todo, Task } from "@/utils/declarations";
import { Component, Prop, Vue } from "vue-property-decorator";
import TodoProgress from "@/components/TodoProgress.vue";

@Component({
  components: {
    TodoProgress,
  },
})
export default class InputSection extends Vue {
  newTask = "";

  @Prop() item!: Todo;

  addTask(name: string) {
    this.newTask = "";
    this.$emit("addTask", name, this.item.id);
  }

  removeTask(task: Task) {
    this.$emit("removeTask", task.id, this.item.id);
  }

  toggleTask(task: Task) {
    this.$emit("toggleTask", task, this.item.id);
  }
}
</script>

<style scoped lang="scss">
@import "../scss/variables";
.inputsection {
  display: flex;
  flex-direction: column;
  flex: 1;
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
  padding: $l $xxl ($xxl * 2) 0;
}
.title:focus,
.description:focus {
  outline: none;
}
.checklist {
  padding-inline-start: 0;
  list-style: none;
  max-width: 350px;

  li {
    display: flex;
    align-items: flex-start;
    margin-bottom: 8px;
  }

  input[type="checkbox"] {
    margin-right: 12px;
    cursor: pointer;
  }

  label {
    flex-grow: 1;
    cursor: pointer;
  }

  div {
    margin-right: 24px;
    flex-grow: 1;
    display: flex;
    border-radius: 20px;
    border: 1px solid lightgray;
    padding: 6px 10px;
  }

  button {
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: white;
    font-size: 22px;
    line-height: 22px;
    border: 0;
    cursor: pointer;
    margin-top: 4px;
  }
}

div.progress-wrapper {
  margin-top: 16px;
}
</style>
