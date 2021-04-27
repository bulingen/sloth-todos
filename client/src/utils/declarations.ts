export type Todo = {
    description: string;
    id: number;
    title: string;
    tasks?: Task[] | undefined;
}

export type Task = {
    name: string;
    id: number;
    completed: boolean;
}