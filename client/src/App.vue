<template>
  <div id="app">
    <div id="header">
      <img class="logo" src="@/assets/sloth.svg" />
      <div class="logoTxt">Sloth Todos</div>
    </div>
    <div class="lowerSection">
      <List
        :data="listData"
        v-on:activeTodo="getActiveTodo($event)"
        v-on:newTodo="newTodo"
      />
      <InputSection
        v-if="currentTodo"
        :item="currentTodo"
        v-on:update="inputSectionUpdate"
        v-on:addTask="addTodoTask"
        v-on:removeTask="removeTodoTask"
        v-on:toggleTask="toggleTodoTask"
      />
      <div class="placeholder" v-if="!currentTodo">
        <img class="logo" src="@/assets/sloth.svg" />
        <div class="logoTxt">Sloth Todos</div>
      </div>
    </div>
    <div
      :class="{ 'actionBtn delete': true, isActive: deleteActive }"
      @click="setTodo('DELETE', currentTodo)"
      title="Delete todo"
    >
      <svg height="24" width="24" viewBox="0 0 24 24">
        <path
          d="M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z"
          fill="#fff"
        />
      </svg>
    </div>
    <div
      :class="{ 'actionBtn save': true, isActive: saveActive }"
      @click="setTodo(currentTodo.id < 0 ? 'POST' : 'PUT', currentTodo)"
      title="Save todo"
    >
      <svg height="24" width="24" viewBox="0 0 24 24">
        <path
          d="M9 16.2L4.8 12l-1.4 1.4L9 19 21 7l-1.4-1.4L9 16.2z"
          fill="#fff"
        />
      </svg>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { Todo, Task } from "@/utils/declarations.ts";
import List from "@/components/List.vue";
import InputSection from "@/components/Todo.vue";
@Component({
  components: {
    List,
    InputSection,
  },
})
export default class App extends Vue {
  url = `http://localhost:8088/todos`;
  listData: Todo[] = [];
  currentTodo: Todo | null = null;
  saveActive = false;
  deleteActive = false;

  async created() {
    const data: Todo[] = await this.callAPI(this.url);
    this.listData = data;
  }

  async callAPI(goToURL: string): Promise<Todo[]> {
    try {
      const response = await fetch(goToURL, { mode: "cors" });
      const json = await response.json();
      return json;
    } catch (error) {
      console.log(error);
      return Promise.reject(error);
    }
  }

  async setTodo(method: string, todo: Todo) {
    console.log(method, todo);
    const data = {
      title: todo.title,
      description: todo.description,
    };

    if (method === "DELETE") {
      this.currentTodo = null;
      this.saveActive = false;
      this.deleteActive = false;
    }

    const url = todo.id < 0 ? this.url : `${this.url}/${todo.id}`;

    const response = await fetch(url, {
      method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    });

    if (method === "DELETE") {
      this.listData = this.listData.filter((item) => item.id !== todo.id);
    } else {
      const json: Todo = await response.json();
      const existing = this.listData.find((t) => t.id === json.id);
      if (existing) {
        const index = this.listData.indexOf(existing);
        Vue.set(this.listData, index, json);
      } else {
        this.listData.push(json);
      }
    }
  }

  getActiveTodo(item: number) {
    console.log("Get active");
    this.currentTodo = this.listData[item];
    this.deleteActive = this.currentTodo?.id ? true : false;
  }

  inputSectionUpdate() {
    this.saveActive = true;
  }

  newTodo() {
    console.log("new");
    this.currentTodo = {
      id: -1,
      title: "",
      description: "",
    };
  }

  async addTodoTask(name: string, todoId: number) {
    const data = { name, todoId };
    const response = await fetch("http://localhost:8088/tasks", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    });
    const json = await response.json();
    const todo = this.listData.find((t) => t.id === todoId);
    if (todo?.tasks?.push) {
      todo?.tasks.push(json);
    }
  }

  async removeTodoTask(taskId: number, todoId: number) {
    await fetch(`http://localhost:8088/tasks/${taskId}`, {
      method: "DELETE",
      headers: { "Content-Type": "application/json" },
    });
    const todo = this.listData.find((t) => t.id === todoId);
    if (todo?.tasks?.filter) {
      todo.tasks = todo.tasks.filter((t) => t.id !== taskId);
    }
  }

  async toggleTodoTask(task: Task, todoId: number) {
    const data = { ...task, completed: !task.completed };
    const response = await fetch(`http://localhost:8088/tasks/${task.id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    });
    const json = await response.json();
    const todo = this.listData.find((t) => t.id === todoId);
    if (todo?.tasks?.find) {
      const existing = todo.tasks.find((t) => t.id === task.id);
      if (existing) {
        const index = todo.tasks.indexOf(existing);
        Vue.set(todo.tasks, index, json);
      }
    }
  }
}
</script>

<style lang="scss">
@import "./scss/variables";
body,
input,
textarea,
button {
  font-family: Avenir, Helvetica, Arial, sans-serif;
}
body {
  margin: 0;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: $txtColor;
  height: 100vh;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}
#header {
  background: $primary;
  display: flex;
  align-items: center;
  height: 64px;
  padding: 0 $m;
  .logo {
    width: 48px;
    margin-right: $m;
  }
}
.logoTxt {
  font-size: 32px;
  font-weight: 900;
  color: $action;
  letter-spacing: -0.08333rem;
}
.lowerSection {
  flex: 1;
  display: flex;
}
.placeholder {
  align-items: center;
  justify-content: center;
  display: flex;
  flex-direction: column;
  background-color: lighten($action, 40%);
  flex: 1;
  img {
    width: 128px;
  }
}
.actionBtn {
  border-radius: 50%;
  box-shadow: 0 3px 5px -1px rgba(0, 0, 0, 0.2),
    0 6px 10px 0 rgba(0, 0, 0, 0.14), 0 1px 18px 0 rgba(0, 0, 0, 0.12),
    0 0 2px hsla(50, 100%, 50%, 0.5) inset;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: $action;
  width: 48px;
  height: 48px;
  transform: translateY(200%);
  font-weight: 900;
  color: #fff;
  user-select: none;
  transition-duration: 0.5s;
  transition-timing-function: cubic-bezier(0.075, 0.82, 0.165, 1);
  position: fixed;
  bottom: $xl;
  &.save {
    right: $xl;
  }
  &.delete {
    right: $xl + 48px + $l;
  }
  &.isActive {
    transform: translateY(0);
  }
}
</style>
